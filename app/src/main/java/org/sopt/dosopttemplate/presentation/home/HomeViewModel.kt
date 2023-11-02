package org.sopt.dosopttemplate.presentation.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.entity.home.Profile
import org.sopt.dosopttemplate.data.repository.Home.HomeRepository
import sopt.uni.util.extension.showSnackbar
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel(){

    private var _profileData = MutableLiveData<List<Profile>>()
    val profileData: LiveData<List<Profile>> = _profileData


    fun getProfileList() {
        viewModelScope.launch {
            kotlin.runCatching {
                val result = homeRepository.getProfileList()
                result.getOrThrow()
            }.fold(
                onSuccess = { profileList ->
                    val today = LocalDate.now()
                    val processedProfileList = profileList.map { profile ->
                        if (profile is Profile.FriendProfile) {
                            val isTodayBirthday = profile.birthday.month == today.month && profile.birthday.dayOfMonth == today.dayOfMonth
                            val isMusicRegist = profile.music != null
                            profile.copy(isTodayBirthday = isTodayBirthday,  isMusicRegist = isMusicRegist)
                        } else {
                            profile
                        }
                    }
                    _profileData.value = processedProfileList
                },
                onFailure = {
                    // TODO: 실패시 예외처리
                },
            )
        }
    }

}

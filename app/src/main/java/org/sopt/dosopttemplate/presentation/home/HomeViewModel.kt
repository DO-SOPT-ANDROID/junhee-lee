package org.sopt.dosopttemplate.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.entity.home.FriendProfileEntity
import org.sopt.dosopttemplate.data.entity.home.Profile
import org.sopt.dosopttemplate.data.entity.home.Profile.FriendProfile.Companion.toFriendProfile
import org.sopt.dosopttemplate.data.repository.home.HomeRepository
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _profileData = MutableLiveData<List<Profile>>()
    val profileData: LiveData<List<Profile>> = _profileData

    private fun updateProfileData(updatedProfiles: List<Profile>) {
        _profileData.value = updatedProfiles
    }

    private fun processProfileList(profileList: List<Profile>): List<Profile> {
        val today = LocalDate.now()
        return profileList.map { profile ->
            if (profile is Profile.FriendProfile) {
                val isTodayBirthday =
                    profile.birthday.month == today.month && profile.birthday.dayOfMonth == today.dayOfMonth
                val isMusicRegist = profile.music?.isNotEmpty() == true
                profile.copy(
                    isTodayBirthday = isTodayBirthday,
                    isMusicRegist = isMusicRegist
                )
            } else {
                profile
            }
        }
    }

    fun initProfileList() {
        viewModelScope.launch {
            kotlin.runCatching {
                val result = homeRepository.getDefaultProfile()
                result.getOrThrow()
            }.onSuccess { profileList ->
                updateProfileData(processProfileList(profileList))
            }.onFailure { exception ->
                // TODO: 실패시 예외처리
            }
        }
    }

    fun fetchProfileList() {
        viewModelScope.launch {
            kotlin.runCatching {
                val result = homeRepository.getProfileList()
                result.getOrThrow()
            }.onSuccess { profileList ->
                val processedProfileList = processProfileList(toFriendProfile(profileList))
                val combinedList = (_profileData.value.orEmpty() + processedProfileList)
                    .distinctBy { it.name }
                updateProfileData(combinedList)
            }.onFailure { exception ->
                // TODO: 실패시 예외처리
            }
        }
    }

    fun addFriend(friendProfile: FriendProfileEntity) {
        viewModelScope.launch {
            kotlin.runCatching {
                homeRepository.addFriend(friendProfile)
            }.onSuccess {
                val newFriendProfile = Profile.FriendProfile(
                    profileImage = friendProfile.imageUri,
                    name = friendProfile.name,
                    birthday = friendProfile.birthday,
                    music = friendProfile.music,
                    isTodayBirthday = friendProfile.isTodayBirthday,
                    isMusicRegist = friendProfile.isMusicRegist
                )
                val currentList = _profileData.value.orEmpty().toMutableList()
                currentList.add(newFriendProfile)
                updateProfileData(currentList)
            }.onFailure { exception ->
                // TODO: 실패시 예외처리
            }
        }
    }

    fun deleteProfile(name: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                homeRepository.deleteFriendByName(name)
            }.onSuccess {
                val updatedList = _profileData.value.orEmpty().filterNot { it.name == name }
                updateProfileData(updatedList)
            }.onFailure { exception ->
                // TODO: 실패시 예외처리
            }
        }
    }
}

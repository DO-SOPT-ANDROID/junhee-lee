package org.sopt.dosopttemplate.presentation.follower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.entity.follower.FollowerData
import org.sopt.dosopttemplate.data.repository.follower.FollowerRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class FollowerViewModel @Inject constructor(
    private val followerRepository: FollowerRepository,
) : ViewModel() {

    private val _followerList = MutableLiveData<UiState<List<FollowerData>>>()
    val followerList: LiveData<UiState<List<FollowerData>>> = _followerList

    fun getFollowerList() {
        viewModelScope.launch {
            followerRepository.getFollowerList()
                .onSuccess { followerList ->
                    _followerList.value = UiState.Success(followerList.data.toList())
                }.onFailure { throwable ->
                    _followerList.value = throwable.message?.let { UiState.Failure(it) }
                }
        }
    }

}
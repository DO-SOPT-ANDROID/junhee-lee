package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.entity.auth.AuthInfo
import org.sopt.dosopttemplate.data.repository.auth.AuthRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _signInResult = MutableLiveData<UiState<AuthInfo>>()
    val signInResult: LiveData<UiState<AuthInfo>> = _signInResult

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            authRepository.postSignInInfo(
                username = username,
                password = password,
            ).onSuccess { authInfo ->
                _signInResult.value = UiState.Success(authInfo)
            }.onFailure { throwable ->
                _signInResult.value = throwable.message?.let { UiState.Failure(it) }
            }
        }
    }
}

package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.repository.auth.AuthRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _signUpResult = MutableLiveData<UiState<Boolean>>()
    val signUpResult: LiveData<UiState<Boolean>> = _signUpResult

    fun signUp(username: String, password: String, nickname: String) {
        viewModelScope.launch {
            authRepository.postSignUpInfo(
                username = username,
                password = password,
                nickname = nickname
            ).onSuccess { _signUpResult.value = UiState.Success(true) }

                .onFailure { throwable ->
                    _signUpResult.value = throwable.message?.let { UiState.Failure(it) }
                }
        }
    }
}

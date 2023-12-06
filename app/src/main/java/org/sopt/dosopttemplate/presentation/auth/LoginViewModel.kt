package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.entity.auth.AuthInfo
import org.sopt.dosopttemplate.data.repository.auth.AuthRepository
import org.sopt.dosopttemplate.util.UiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _signInState = MutableStateFlow<UiState<AuthInfo>>(UiState.Loading)
    val signInState get() = _signInState.asStateFlow()

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            authRepository.postSignInInfo(
                username = username,
                password = password,
            ).onSuccess { authInfo ->
                _signInState.value = UiState.Success(authInfo)
            }.onFailure { throwable ->
                Timber.e(throwable.message)
            }
        }
    }
}

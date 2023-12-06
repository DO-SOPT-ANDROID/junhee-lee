package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.repository.auth.AuthRepository
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.extension.addSourceList
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _signUpState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val signUpState get() = _signUpState.asStateFlow()

    val id = MutableLiveData("")
    val pw = MutableLiveData("")
    val nickname = MutableLiveData("")
    val mbti = MutableLiveData("")

    val isEnabledLoginButton = MediatorLiveData<Boolean>().apply {
        addSourceList(id, pw, nickname, mbti) { isValidSignUpInfo() }
    }

    val nicknameLength = nickname.map {
        it.length
    }

    fun signUp() {
        viewModelScope.launch {
            authRepository.postSignUpInfo(
                username = id.value.toString(),
                password = pw.value.toString(),
                nickname = nickname.value.toString()
            ).onSuccess { _signUpState.value = UiState.Success(true) }

                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }

    private fun isValidSignUpInfo() = isValidateId(id.value) && isValidatePassWord(pw.value)
            && isValidateNickName() && isValidateMbti()

    fun isValidateId(id: String?): Boolean {
        return id.isNullOrEmpty() || ID_REGEX.matcher(id).matches()
    }

    fun isValidatePassWord(pw: String?): Boolean {
        return pw.isNullOrEmpty() || PW_REGEX.matcher(pw).matches()
    }

    private fun isValidateNickName(): Boolean {
        return nickname.value?.isNotBlank() ?: false
    }

    private fun isValidateMbti(): Boolean {
        return mbti.value?.isNotBlank() ?: false
    }

    companion object {
        private const val ID_PATTERN = "(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,10}"
        private const val PW_PATTERN =
            "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#%^&*()])[a-zA-Z0-9!@#%^&*()]{6,12}"
        val ID_REGEX: Pattern = Pattern.compile(ID_PATTERN)
        val PW_REGEX: Pattern = Pattern.compile(PW_PATTERN)
    }
}

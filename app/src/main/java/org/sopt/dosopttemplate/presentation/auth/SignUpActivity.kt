package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.datasource.local.DoSoptStorage
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.setOnSingleClickListener
import org.sopt.dosopttemplate.util.extension.showSnackbar
import java.util.regex.Pattern

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {

    private val signUpViewModel: SignUpViewModel by viewModels()
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var name: String
    private lateinit var mbti: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        clickSignUpEnd()
    }

    private fun clickSignUpEnd() {
        binding.btnSignupEnd.setOnSingleClickListener {
            getSignUpInfo()
            if (isValidateForm()) {
                signUpViewModel.signUp(id, pw, name)
                observeSignUp()
            } else {
                showSnackbar(binding.root, getString(R.string.signup_error_message))
            }
        }
    }

    private fun observeSignUp() {
        signUpViewModel.signUpResult.observe(this) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    setResult(RESULT_OK, intent)
                    DoSoptStorage.mbti = mbti
                    finish()
                }

                is UiState.Failure -> {
                    showSnackbar(binding.root, getString(R.string.signup_fail_message))
                }

                else -> {}
            }
        }
    }

    private fun getSignUpInfo() {
        with(binding) {
            id = etSignupId.text.toString()
            pw = etSignupPw.text.toString()
            name = etSignupName.text.toString()
            mbti = etSignupMbti.text.toString()
        }
    }

    private fun isValidateForm(): Boolean {

        return isValidateId(id) && isValidatePassWord(pw) && isValidateNickName(name) &&
                isValidateMbti(mbti)
    }

    private fun isValidateId(id: String): Boolean {
        return id.isNotBlank() && ID_REGEX.matcher(id).matches()
    }

    private fun isValidatePassWord(pw: String): Boolean {
        return pw.isNotBlank() && PW_REGEX.matcher(pw).matches()
    }

    private fun isValidateNickName(name: String): Boolean {
        return name.isNotBlank()
    }

    private fun isValidateMbti(mbti: String): Boolean {
        return mbti.isNotBlank()
    }

    companion object {
        private const val ID_PATTERN = "(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,10}"
        private const val PW_PATTERN =
            "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#%^&*()])[a-zA-Z0-9!@#%^&*()]{8,12}"
        val ID_REGEX: Pattern = Pattern.compile(ID_PATTERN)
        val PW_REGEX: Pattern = Pattern.compile(PW_PATTERN)
    }
}

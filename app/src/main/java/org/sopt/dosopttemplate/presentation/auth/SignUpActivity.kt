package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.datasource.local.DoSoptStorage
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.setOnSingleClickListener
import org.sopt.dosopttemplate.util.extension.setVisible
import org.sopt.dosopttemplate.util.extension.showSnackbar

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {

    private val signUpViewModel: SignUpViewModel by viewModels()
    private lateinit var mbti: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.signupViewModel = signUpViewModel

        clickSignUpEnd()
        observeId()
        observePw()
        observeSignUpButton()
    }

    private fun observeSignUpButton() {
        signUpViewModel.isEnabledLoginButton.observe(this) {
            binding.btnSignupEnd.isEnabled = it
        }
    }

    private fun observePw() {
        signUpViewModel.pw.observe(this) {
            if (signUpViewModel.isValidatePassWord(it)) {
                binding.tvSignupPasswordErrorMessage.setVisible(INVISIBLE)
                binding.etSignupPw.backgroundTintList =
                    ContextCompat.getColorStateList(this, R.color.Gray_500)
            } else {
                binding.tvSignupPasswordErrorMessage.setVisible(VISIBLE)
                binding.etSignupPw.backgroundTintList =
                    ContextCompat.getColorStateList(this, R.color.Pink_500)
            }
        }
    }

    private fun observeId() {
        signUpViewModel.id.observe(this) {
            if (signUpViewModel.isValidateId(it)) {
                binding.tvSignupIdErrorMessage.setVisible(INVISIBLE)
                binding.etSignupId.backgroundTintList =
                    ContextCompat.getColorStateList(this, R.color.Gray_500)
            } else {
                binding.tvSignupIdErrorMessage.setVisible(VISIBLE)
                binding.etSignupId.backgroundTintList =
                    ContextCompat.getColorStateList(this, R.color.Pink_500)
            }
        }
    }

    private fun clickSignUpEnd() {
        binding.btnSignupEnd.setOnSingleClickListener {
            getSignUpInfo()
            signUpViewModel.signUp()
            observeSignUp()
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
            mbti = etSignupMbti.text.toString()
        }
    }


}

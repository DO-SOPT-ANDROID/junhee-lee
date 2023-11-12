package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.entity.sign.SignInfo
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.presentation.auth.LoginActivity.Companion.SIGN_INFO
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.hideKeyboard
import org.sopt.dosopttemplate.util.extension.setOnSingleClickListener
import org.sopt.dosopttemplate.util.extension.showSnackbar

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        clickSignUpEnd()
        clickLayout()
    }

    private fun clickLayout() {
        binding.layoutSignup.setOnSingleClickListener {
            hideKeyboard(currentFocus ?: View(this))
        }
    }

    private fun clickSignUpEnd() {
        binding.btnSignupEnd.setOnSingleClickListener {
            if (isValidateForm()) {
                val intent = Intent(this, LoginActivity::class.java)
                val signInfo = SignInfo(
                    binding.etSignupId.text.toString(),
                    binding.etSignupPw.text.toString(),
                    binding.etSignupName.text.toString(),
                    binding.etSignupMbti.text.toString(),
                )
                intent.putExtra(SIGN_INFO, signInfo)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                showSnackbar(binding.root, getString(R.string.signup_error_message))
            }
        }
    }

    private fun isValidateForm(): Boolean {
        val id = binding.etSignupId.text.toString()
        val pw = binding.etSignupPw.text.toString()
        val name = binding.etSignupName.text.toString()
        val mbti = binding.etSignupMbti.text.toString()

        return isValidateId(id) && isValidatePassWord(pw) && isValidateNickName(name) &&
            isValidateMbti(mbti)
    }

    private fun isValidateId(id: String): Boolean {
        return id.isNotBlank() && id.length in 6..10
    }

    private fun isValidatePassWord(pw: String): Boolean {
        return pw.isNotBlank() && pw.length in 8..12
    }

    private fun isValidateNickName(name: String): Boolean {
        return name.isNotBlank()
    }

    private fun isValidateMbti(mbti: String): Boolean {
        return mbti.isNotBlank()
    }
}

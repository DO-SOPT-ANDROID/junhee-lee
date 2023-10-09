package org.sopt.dosopttemplate.presentation

import android.content.Intent
import android.os.Bundle
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.entity.sign.SignInfo
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showSnackbar

class SignUpActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        clickSignUpEnd()
    }

    private fun clickSignUpEnd() {
        binding.btnSignupEnd.setOnSingleClickListener {
            if (isValidateForm()) {
                val intent = Intent(this, LoginActivity::class.java)
                val signInfo = SignInfo(
                    binding.etId.text.toString(),
                    binding.etPw.text.toString(),
                    binding.etName.text.toString(),
                    binding.etMbti.text.toString(),
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
        val id = binding.etId.text.toString()
        val pw = binding.etPw.text.toString()
        val name = binding.etName.text.toString()
        val mbti = binding.etMbti.text.toString()

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

    companion object {
        const val SIGN_INFO = "sign_info"
    }
}

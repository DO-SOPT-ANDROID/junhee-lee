package org.sopt.dosopttemplate.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.entity.sign.SignInfo
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.SignUpActivity.Companion.SIGN_INFO
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.parcelable
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showSnackbar
import sopt.uni.util.extension.showToast

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var id: String? = ""
    private var password: String? = ""
    private var name: String? = ""
    private var mbti: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setResultSignUp()
        moveToSignUp()
        clickLoginButton()
    }

    private fun setResultSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val signData = result.data ?: return@registerForActivityResult
                    val signInfo = signData.parcelable<SignInfo>(SIGN_INFO)

                    if (signInfo != null) {
                        id = signInfo.id
                        password = signInfo.password
                        name = signInfo.name
                        mbti = signInfo.mbti
                    }
                    showSnackbar(binding.root, getString(R.string.signup_complete_message))
                }
            }
    }

    private fun clickLoginButton() {
        binding.btnLogin.setOnSingleClickListener {
            checkLoginValid()
        }
    }

    private fun checkLoginValid() {
        if (binding.etId.text.toString() == id && binding.etPw.text.toString() == password) {
            val intent = Intent(this, MainActivity::class.java)
            showToast(getString(R.string.login_success))
            intent.putExtra("name", name)
            intent.putExtra("id", id)
            intent.putExtra("mbti", mbti)
            startActivity(intent)
        } else {
            showToast(getString(R.string.login_fail))
        }
    }

    private fun moveToSignUp() {
        binding.btnSignup.setOnSingleClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}

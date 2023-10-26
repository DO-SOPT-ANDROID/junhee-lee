package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.datasource.local.DoSoptStorage
import org.sopt.dosopttemplate.data.entity.sign.SignInfo
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.hideKeyboard
import sopt.uni.util.extension.parcelable
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showSnackbar
import sopt.uni.util.extension.showToast

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var signData: SignInfo
    private var backPressedTime = 0L

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - backPressedTime <= 2000) {
                finish()
            } else {
                backPressedTime = System.currentTimeMillis()
                showToast(getString(R.string.application_terminate))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        autoLogin()
        setResultSignUp()
        moveToSignUp()
        clickLoginButton()
        clickLayout()
        finishApplication()
    }

    private fun finishApplication() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun autoLogin() {
        val savedId = DoSoptStorage.getUserInfo()?.id
        val savedPw = DoSoptStorage.getUserInfo()?.password
        val savedName = DoSoptStorage.getUserInfo()?.name
        val savedMbti = DoSoptStorage.getUserInfo()?.mbti

        if (savedId != null && savedPw != null) {
            val signInfo = SignInfo(
                savedId,
                savedPw,
                savedName.toString(),
                savedMbti.toString(),
            )
            moveToMainPage(signInfo)
        }
    }

    private fun clickLayout() {
        binding.layoutLogin.setOnSingleClickListener {
            hideKeyboard(currentFocus ?: View(this))
        }
    }

    private fun setResultSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    signData = result.data?.parcelable<SignInfo>(SIGN_INFO)
                        ?: return@registerForActivityResult
                    showSnackbar(binding.root, getString(R.string.signup_complete_message))
                }
            }
    }

    private fun clickLoginButton() {
        binding.btnLogin.setOnSingleClickListener {
            if (checkLoginValid()) {
                moveToMainPage(signData)
            }
        }
    }

    private fun checkLoginValid(): Boolean {
        return if (binding.etLoginId.text.toString() == signData.id && binding.etLoginPw.text.toString() == signData.password) {
            saveSignInfo()
            true
        } else {
            showToast(getString(R.string.login_fail))
            false
        }
    }

    private fun saveSignInfo() {
        if (binding.cbLogin.isChecked) {
            DoSoptStorage.setUserInfo(signData)
        }
    }

    private fun moveToMainPage(userData: SignInfo) {
        val intent = Intent(this, HomeActivity::class.java)
        showToast(getString(R.string.login_success))
        intent.putExtra(SIGN_INFO, userData)
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun moveToSignUp() {
        binding.btnSignup.setOnSingleClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    companion object {
        const val SIGN_INFO = "sign_info"
    }
}
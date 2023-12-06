package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.datasource.local.DoSoptStorage
import org.sopt.dosopttemplate.data.entity.auth.AuthInfo
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.setOnSingleClickListener
import org.sopt.dosopttemplate.util.extension.showSnackbar
import org.sopt.dosopttemplate.util.extension.showToast

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var backPressedTime = ZERO

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - backPressedTime <= TWO_SECONDS) {
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

        checkAutoLogin()
        setResultSignUp()
        moveToSignUp()
        clickLoginButton()
        finishApplication()
        observeLogin()
    }

    private fun finishApplication() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun setResultSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    showSnackbar(binding.root, getString(R.string.signup_complete_message))
                }
            }
    }

    private fun clickLoginButton() {
        binding.btnLogin.setOnSingleClickListener {
            val userId = binding.etLoginId.text.toString()
            val userPw = binding.etLoginPw.text.toString()

            loginViewModel.signIn(userId, userPw)
        }
    }

    private fun observeLogin() {
        loginViewModel.signInResult.observe(this) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    setAutoLogin(uiState.data)
                    moveToHome(uiState.data)
                }

                is UiState.Failure -> {
                    showSnackbar(binding.root, getString(R.string.login_fail))
                }

                else -> {}
            }
        }
    }

    private fun setAutoLogin(signData: AuthInfo) {
        if (binding.cbLogin.isChecked) {
            DoSoptStorage.setUserInfo(signData)
            DoSoptStorage.isLogin = true
        }
    }

    private fun checkAutoLogin() {
        if (DoSoptStorage.isLogin) {
            val userData = DoSoptStorage.getUserInfo()
            moveToHome(userData)
        }
    }

    private fun moveToHome(userData: AuthInfo) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(SIGN_INFO, userData)
            addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
        }
        showToast(getString(R.string.login_success) + if (DoSoptStorage.userId == 0) userData.id else DoSoptStorage.userId)
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
        const val ZERO = 0L
        const val TWO_SECONDS = 2000
    }
}

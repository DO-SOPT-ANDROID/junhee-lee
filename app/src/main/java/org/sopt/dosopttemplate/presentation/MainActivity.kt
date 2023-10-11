package org.sopt.dosopttemplate.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.datasource.local.DoSoptStorage
import org.sopt.dosopttemplate.data.entity.sign.SignInfo
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.SIGN_INFO
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.parcelable
import sopt.uni.util.extension.setOnSingleClickListener
import sopt.uni.util.extension.showToast

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

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

        initMainPageSetting()
        logout()
        finishApplication()
    }

    private fun finishApplication() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun initMainPageSetting() {
        val userInfo = intent.extras?.parcelable<SignInfo>(SIGN_INFO)

        if (userInfo != null) {
            binding.tvMainpageNickname.text = userInfo.name
            binding.tvMainpageId.text = userInfo.id
            binding.tvMainpageMbti.text = userInfo.mbti
        }
    }

    private fun logout() {
        binding.tvMainpageLogout.setOnSingleClickListener {
            DoSoptStorage.userInfoClear()
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}

package org.sopt.dosopttemplate.presentation

import android.content.Intent
import android.os.Bundle
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.datasource.local.DoSoptStorage
import org.sopt.dosopttemplate.data.entity.sign.SignInfo
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.presentation.LoginActivity.Companion.SIGN_INFO
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.parcelable
import sopt.uni.util.extension.setOnSingleClickListener

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initMainPageSetting()
        logout()
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

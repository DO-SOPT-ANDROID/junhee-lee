package org.sopt.dosopttemplate.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import sopt.uni.util.binding.BindingActivity

class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {

//    private var backPressedTime = 0L
//
//    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
//        override fun handleOnBackPressed() {
//            if (System.currentTimeMillis() - backPressedTime <= 2000) {
//                finish()
//            } else {
//                backPressedTime = System.currentTimeMillis()
//                showToast(getString(R.string.application_terminate))
//            }
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        initMainPageSetting()
//        logout()
//        finishApplication()
        clickBottomNavigation()

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_home)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_home, HomeFragment())
                .commit()
        }
    }

    private fun clickBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_do_android -> {
                    replaceFragment(DoAndroidFragment())
                    true
                }

                R.id.menu_mypage -> {
                    replaceFragment(MyPageFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }

//    private fun finishApplication() {
//        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
//    }
//
//    private fun initMainPageSetting() {
//        val userInfo = intent.extras?.parcelable<SignInfo>(SIGN_INFO)
//
//        if (userInfo != null) {
//            binding.tvMainpageNickname.text = userInfo.name
//            binding.tvMainpageId.text = userInfo.id
//            binding.tvMainpageMbti.text = userInfo.mbti
//        }
//    }
//
//    private fun logout() {
//        binding.tvMainpageLogout.setOnSingleClickListener {
//            DoSoptStorage.userInfoClear()
//            val intent = Intent(this, LoginActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
//        }
//    }
}

package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import org.sopt.dosopttemplate.presentation.android.DoAndroidFragment
import org.sopt.dosopttemplate.presentation.auth.LoginActivity.Companion.TWO_SECONDS
import org.sopt.dosopttemplate.presentation.auth.LoginActivity.Companion.ZERO
import org.sopt.dosopttemplate.presentation.follower.FollowerFragment
import org.sopt.dosopttemplate.presentation.mypage.MyPageFragment
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.showToast

@AndroidEntryPoint
class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {

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

        initFragment()
        initBottomNavi()
        clickBottomNavigation()
        finishApplication()
        doubleClickBottomNavigation()
    }

    private fun doubleClickBottomNavigation() {
        binding.bnvHome.setOnItemReselectedListener {
            supportFragmentManager.findFragmentById(R.id.fcv_home)?.let { currentFragment ->
                when (currentFragment) {
                    is HomeFragment -> {
                        currentFragment.goToTop()
                    }

                    is FollowerFragment -> {
                        currentFragment.goToTop()
                    }
                }
            }
        }

    }

    private fun initFragment() {
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
                R.id.menu_home -> navigateTo<HomeFragment>()
                R.id.menu_do_android -> navigateTo<DoAndroidFragment>()
                R.id.menu_mypage -> navigateTo<MyPageFragment>()
                R.id.menu_follower -> navigateTo<FollowerFragment>()
                else -> false
            }
            true
        }
    }

    private fun finishApplication() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun initBottomNavi() {
        binding.bnvHome.selectedItemId = R.id.menu_home
    }

    private inline fun <reified T : Fragment> navigateTo() {
        supportFragmentManager.commit {
            replace<T>(R.id.fcv_home, T::class.java.canonicalName)
        }
    }
}

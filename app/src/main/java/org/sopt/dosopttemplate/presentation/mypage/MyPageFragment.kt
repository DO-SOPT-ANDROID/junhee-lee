package org.sopt.dosopttemplate.presentation.mypage

import android.os.Bundle
import android.view.View
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentMypageBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

class MyPageFragment : BindingFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initMainPageSetting()
        // logout()
    }

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

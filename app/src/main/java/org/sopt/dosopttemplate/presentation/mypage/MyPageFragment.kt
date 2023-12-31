package org.sopt.dosopttemplate.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.datasource.local.DoSoptStorage
import org.sopt.dosopttemplate.data.entity.auth.AuthInfo
import org.sopt.dosopttemplate.databinding.FragmentMypageBinding
import org.sopt.dosopttemplate.presentation.auth.LoginActivity
import org.sopt.dosopttemplate.presentation.auth.LoginActivity.Companion.SIGN_INFO
import org.sopt.dosopttemplate.util.binding.BindingFragment
import org.sopt.dosopttemplate.util.extension.parcelable
import org.sopt.dosopttemplate.util.extension.setOnSingleClickListener

class MyPageFragment : BindingFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMainPageSetting()
        logout()
    }

    private fun initMainPageSetting() {
        val intent = requireActivity().intent
        val userInfo = intent.extras?.parcelable<AuthInfo>(SIGN_INFO)

        if (userInfo != null) {
            binding.tvMainpageNickname.text = userInfo.name
            binding.tvMainpageId.text = userInfo.username
            binding.tvMainpageMbti.text = DoSoptStorage.mbti
        }
    }

    private fun logout() {
        binding.tvMainpageLogout.setOnSingleClickListener {
            DoSoptStorage.userInfoClear()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }


}

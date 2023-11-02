package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.entity.home.Profile
import org.sopt.dosopttemplate.databinding.ActivityFriendProfileDetailBinding
import org.sopt.dosopttemplate.presentation.home.HomeFragment.Companion.PROFILE_LIST
import sopt.uni.util.binding.BindingActivity
import sopt.uni.util.extension.parcelable
import sopt.uni.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class HomeProfileDetailActivity :
    BindingActivity<ActivityFriendProfileDetailBinding>(R.layout.activity_friend_profile_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initProfileDetail()
        setupBackButton()
    }

    private fun initProfileDetail() {
        val profileDetail = intent.parcelable<Profile.FriendProfile>(PROFILE_LIST)
        with(binding) {
            if (profileDetail != null) {
                ivFriendDetailProfile.load(profileDetail.profileImage)
                tvFriendDetailName.text = profileDetail.name

                if (profileDetail.isMusicRegist) {
                    clFriendDetailMusic.visibility = View.VISIBLE
                    tvFriendDetailMusicName.text = profileDetail.music
                }

            }
        }
    }

    private fun setupBackButton() {
        binding.btnFriendDetailBack.setOnSingleClickListener {
            finish()
        }
    }

}
package org.sopt.dosopttemplate.presentation.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.entity.home.Profile
import org.sopt.dosopttemplate.databinding.ItemVpHomeFriendProfileBinding
import org.sopt.dosopttemplate.databinding.ItemVpHomeMyprofileBinding
import org.sopt.dosopttemplate.util.extension.setOnSingleClickListener

sealed class HomeVpViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    class MyProfileViewHolder(private val binding: ItemVpHomeMyprofileBinding) :
        HomeVpViewHolder(binding.root) {
        fun onBind(myProile: Profile.MyProfile) {
            binding.ivVpHomeMyprofile.load(myProile.profileImage)
            binding.tvVpHomeMyName.text = myProile.name
        }
    }

    class FriendProfileViewHolder(
        private val binding: ItemVpHomeFriendProfileBinding,
        private val onClick: (Int) -> Unit,
        private val deleteUser: (String) -> Unit,
    ) :
        HomeVpViewHolder(binding.root) {
        fun onBind(friendProfile: Profile.FriendProfile) {
            with(binding) {
                ivVpHomeFriendProfile.load(friendProfile.profileImage)
                tvVpHomeFriendName.text = friendProfile.name

                clVpHomeFriend.setOnSingleClickListener {
                    onClick(absoluteAdapterPosition)
                }

                clVpHomeFriend.setOnLongClickListener {
                    deleteUser(friendProfile.name)
                    true
                }

                if (friendProfile.isTodayBirthday) {
                    ivVpHomeFriendBirthdayCake.visibility = View.VISIBLE
                    tvVpHomeFriendGift.visibility = View.VISIBLE
                    tvVpHomeFriendGift.text = binding.root.context.getString(R.string.home_gift)
                }

                if (friendProfile.isMusicRegist) {
                    clVpHomeFriendMusic.visibility = View.VISIBLE
                    tvVpHomeFriendMusicName.text = friendProfile.music
                }
            }
        }
    }
}
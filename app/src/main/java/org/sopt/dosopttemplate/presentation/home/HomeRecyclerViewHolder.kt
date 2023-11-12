package org.sopt.dosopttemplate.presentation.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.entity.home.Profile
import org.sopt.dosopttemplate.databinding.ItemHomeFriendProfileBinding
import org.sopt.dosopttemplate.databinding.ItemHomeMyproileBinding
import org.sopt.dosopttemplate.util.extension.setOnSingleClickListener

sealed class HomeRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    class MyProfileViewHolder(private val binding: ItemHomeMyproileBinding) :
        HomeRecyclerViewHolder(binding.root) {
        fun onBind(myProile: Profile.MyProfile) {
            binding.ivHomeMyprofile.load(myProile.profileImage)
            binding.tvHomeMyname.text = myProile.name
        }
    }

    class FriendProfileViewHolder(
        private val binding: ItemHomeFriendProfileBinding,
        private val onClick: (Int) -> Unit,
        private val deleteUser: (String) -> Unit,
    ) :
        HomeRecyclerViewHolder(binding.root) {
        fun onBind(friendProfile: Profile.FriendProfile) {
            with(binding) {
                clHomeFriendMusic.visibility = View.GONE
                tvHomeFriendGift.visibility = View.GONE
                ivHomeFriendBirthdayCake.visibility = View.GONE

                ivHomeFriendProfile.load(friendProfile.profileImage)
                tvHomeFriendName.text = friendProfile.name

                clHomeFriend.setOnSingleClickListener {
                    onClick(absoluteAdapterPosition)
                }

                clHomeFriend.setOnLongClickListener {
                    deleteUser(friendProfile.name)
                    true
                }

                if (friendProfile.isTodayBirthday) {
                    ivHomeFriendBirthdayCake.visibility = View.VISIBLE
                    tvHomeFriendGift.visibility = View.VISIBLE
                    tvHomeFriendGift.text =
                        binding.root.context.getString(R.string.home_gift)
                }

                if (friendProfile.isMusicRegist) {
                    clHomeFriendMusic.visibility = View.VISIBLE
                    tvHomeFriendMusicName.text = friendProfile.music
                }
            }
        }
    }
}
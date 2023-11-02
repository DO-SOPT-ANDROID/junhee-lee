package org.sopt.dosopttemplate.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.entity.home.Profile
import org.sopt.dosopttemplate.databinding.ItemVpHomeFriendProfileBinding
import org.sopt.dosopttemplate.databinding.ItemVpHomeMyprofileBinding
import sopt.uni.util.extension.ItemDiffCallback
import sopt.uni.util.extension.setOnSingleClickListener

class HomeVpAdapter(
    private val onClick: (Int) -> Unit,
) : ListAdapter<Profile, RecyclerView.ViewHolder>(
    ItemDiffCallback<Profile>(
        onItemsTheSame = { old, new -> old.name == new.name },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MY_PROFILE_TYPE -> {
                val binding = ItemVpHomeMyprofileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                MyProfileViewHolder(binding)
            }

            FRIEND_PROFILE_TYPE -> {
                val binding = ItemVpHomeFriendProfileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                onClick
                FriendProfileViewHolder(binding)
            }

            else -> throw IllegalArgumentException(UNKNOWN_TYPE)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyProfileViewHolder -> holder.onBind(getItem(position) as Profile.MyProfile)
            is FriendProfileViewHolder -> holder.onBind(getItem(position) as Profile.FriendProfile)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Profile.MyProfile -> MY_PROFILE_TYPE
            is Profile.FriendProfile -> FRIEND_PROFILE_TYPE
            else -> MY_PROFILE_TYPE
        }
    }

    inner class MyProfileViewHolder(private val binding: ItemVpHomeMyprofileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(myProile: Profile.MyProfile) {
            binding.ivVpHomeMyprofile.load(myProile.profileImage)
            binding.tvVpHomeMyName.text = myProile.name
        }
    }

    inner class FriendProfileViewHolder(private val binding: ItemVpHomeFriendProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(friendProfile: Profile.FriendProfile) {
            with(binding) {
                ivVpHomeFriendProfile.load(friendProfile.profileImage)
                tvVpHomeFriendName.text = friendProfile.name

                clVpHomeFriend.setOnSingleClickListener {
                    onClick(absoluteAdapterPosition)
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

    companion object {
        private const val MY_PROFILE_TYPE = 0
        private const val FRIEND_PROFILE_TYPE = 1
        private const val UNKNOWN_TYPE = "Unknown viewType"
    }
}

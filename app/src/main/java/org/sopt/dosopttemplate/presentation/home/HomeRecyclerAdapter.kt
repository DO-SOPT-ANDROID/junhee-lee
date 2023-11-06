package org.sopt.dosopttemplate.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.entity.home.Profile
import org.sopt.dosopttemplate.databinding.ItemHomeFriendProfileBinding
import org.sopt.dosopttemplate.databinding.ItemHomeMyproileBinding
import sopt.uni.util.extension.ItemDiffCallback
import sopt.uni.util.extension.setOnSingleClickListener

class HomeRecyclerAdapter(
    private val onClick: (Int) -> Unit,
    private val deleteUser: (String) -> Unit,
) : ListAdapter<Profile, RecyclerView.ViewHolder>(
    ItemDiffCallback<Profile>(
        onItemsTheSame = { old, new -> old.name == new.name },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MY_PROFILE_TYPE -> {
                val binding = ItemHomeMyproileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                MyProfileViewHolder(binding)
            }

            FRIEND_PROFILE_TYPE -> {
                val binding = ItemHomeFriendProfileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                deleteUser
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

    inner class MyProfileViewHolder(private val binding: ItemHomeMyproileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(myProile: Profile.MyProfile) {
            binding.ivHomeMyprofile.load(myProile.profileImage)
            binding.tvHomeMyname.text = myProile.name
        }
    }

    inner class FriendProfileViewHolder(private val binding: ItemHomeFriendProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(friendProfile: Profile.FriendProfile) {
            with(binding) {
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

    companion object {
        private const val MY_PROFILE_TYPE = 0
        private const val FRIEND_PROFILE_TYPE = 1
        private const val UNKNOWN_TYPE = "Unknown viewType"
    }
}

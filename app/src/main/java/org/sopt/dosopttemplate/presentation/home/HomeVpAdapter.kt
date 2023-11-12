package org.sopt.dosopttemplate.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.dosopttemplate.data.entity.home.Profile
import org.sopt.dosopttemplate.databinding.ItemVpHomeFriendProfileBinding
import org.sopt.dosopttemplate.databinding.ItemVpHomeMyprofileBinding
import org.sopt.dosopttemplate.util.extension.ItemDiffCallback

class HomeVpAdapter(
    private val onClick: (Int) -> Unit,
    private val deleteUser: (String) -> Unit,
) : ListAdapter<Profile, HomeVpViewHolder>(
    ItemDiffCallback<Profile>(
        onItemsTheSame = { old, new -> old.name == new.name },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVpViewHolder {
        return when (viewType) {
            MY_PROFILE_TYPE -> {
                val binding = ItemVpHomeMyprofileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                HomeVpViewHolder.MyProfileViewHolder(binding)
            }

            FRIEND_PROFILE_TYPE -> {
                val binding = ItemVpHomeFriendProfileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                deleteUser
                onClick
                HomeVpViewHolder.FriendProfileViewHolder(binding, onClick, deleteUser)
            }

            else -> throw IllegalArgumentException(UNKNOWN_TYPE)
        }
    }

    override fun onBindViewHolder(holder: HomeVpViewHolder, position: Int) {
        when (holder) {
            is HomeVpViewHolder.MyProfileViewHolder -> holder.onBind(getItem(position) as Profile.MyProfile)
            is HomeVpViewHolder.FriendProfileViewHolder -> holder.onBind(getItem(position) as Profile.FriendProfile)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Profile.MyProfile -> MY_PROFILE_TYPE
            is Profile.FriendProfile -> FRIEND_PROFILE_TYPE
            else -> MY_PROFILE_TYPE
        }
    }

    companion object {
        private const val MY_PROFILE_TYPE = 0
        private const val FRIEND_PROFILE_TYPE = 1
        private const val UNKNOWN_TYPE = "Unknown viewType"
    }
}

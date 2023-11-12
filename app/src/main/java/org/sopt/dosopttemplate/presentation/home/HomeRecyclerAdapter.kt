package org.sopt.dosopttemplate.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.dosopttemplate.data.entity.home.Profile
import org.sopt.dosopttemplate.databinding.ItemHomeFriendProfileBinding
import org.sopt.dosopttemplate.databinding.ItemHomeMyproileBinding
import org.sopt.dosopttemplate.util.extension.ItemDiffCallback

class HomeRecyclerAdapter(
    private val onClick: (Int) -> Unit,
    private val deleteUser: (String) -> Unit,
) : ListAdapter<Profile, HomeRecyclerViewHolder>(
    ItemDiffCallback<Profile>(
        onItemsTheSame = { old, new -> old.name == new.name },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        return when (viewType) {
            MY_PROFILE_TYPE -> {
                val binding = ItemHomeMyproileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                HomeRecyclerViewHolder.MyProfileViewHolder(binding)
            }

            FRIEND_PROFILE_TYPE -> {
                val binding = ItemHomeFriendProfileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
                deleteUser
                onClick
                HomeRecyclerViewHolder.FriendProfileViewHolder(binding, onClick, deleteUser)
            }

            else -> throw IllegalArgumentException(UNKNOWN_TYPE)
        }
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        when (holder) {
            is HomeRecyclerViewHolder.MyProfileViewHolder -> holder.onBind(getItem(position) as Profile.MyProfile)
            is HomeRecyclerViewHolder.FriendProfileViewHolder -> holder.onBind(getItem(position) as Profile.FriendProfile)
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

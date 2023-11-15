package org.sopt.dosopttemplate.presentation.follower

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.data.entity.follower.FollowerData
import org.sopt.dosopttemplate.databinding.ItemFollowerBinding

class FollowerViewHolder(private val binding: ItemFollowerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(follower: FollowerData) {
        with(binding) {
            ivFollower.load(follower.avatar)
            tvFollowerName.text = follower.firstName
            tvFollowerMail.text = follower.email
        }
    }

}
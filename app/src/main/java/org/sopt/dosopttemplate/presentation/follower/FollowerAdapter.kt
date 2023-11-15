package org.sopt.dosopttemplate.presentation.follower

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.dosopttemplate.data.entity.follower.FollowerData
import org.sopt.dosopttemplate.databinding.ItemFollowerBinding
import org.sopt.dosopttemplate.util.extension.ItemDiffCallback

class FollowerAdapter : ListAdapter<FollowerData, FollowerViewHolder>(
    ItemDiffCallback<FollowerData>(
        onItemsTheSame = { old, new -> old == new },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

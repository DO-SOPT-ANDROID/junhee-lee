package org.sopt.dosopttemplate.data.entity.home

import androidx.annotation.DrawableRes

sealed class Profile {
    data class MyProfile(
        @DrawableRes
        val profileImage: Int,
        val name: String,
    ) : Profile()

    data class FriendProfile(
        @DrawableRes
        val profileImage: Int,
        val name: String,
    ) : Profile()
}

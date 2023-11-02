package org.sopt.dosopttemplate.data.entity.home

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

sealed class Profile {

    abstract val name: String?

    data class MyProfile(
        @DrawableRes
        val profileImage: Int,
        override val name: String,
    ) : Profile()

    @Parcelize
    data class FriendProfile(
        @DrawableRes
        val profileImage: Int,
        override val name: String,
        val birthday: LocalDate,
        val music: String?,
        val isTodayBirthday: Boolean,
        val isMusicRegist: Boolean,
    ) : Parcelable, Profile()
}

package org.sopt.dosopttemplate.data.entity.auth

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthInfo(
    val id: Int,
    val username: String = "",
    val password: String = "",
    val name: String = "",
    val mbti: String = "",
) : Parcelable


package org.sopt.dosopttemplate.data.entity.sign

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInfo(
    val id: String,
    val password: String,
    val name: String,
    val mbti: String,
) : Parcelable

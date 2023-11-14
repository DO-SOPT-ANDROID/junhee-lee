package org.sopt.dosopttemplate.data.source.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignInDto(
    @SerialName("id")
    val id: Int,
    @SerialName("username")
    val username: String,
    @SerialName("nickname")
    val nickname: String,
)

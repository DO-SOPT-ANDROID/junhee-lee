package org.sopt.dosopttemplate.data.repository.auth

import org.sopt.dosopttemplate.data.entity.auth.AuthInfo
import retrofit2.Response

interface AuthRepository {

    suspend fun postSignUpInfo(username: String, password: String, nickname: String): Response<Unit>

    suspend fun postSignInInfo(username: String, password: String): Result<AuthInfo>

}

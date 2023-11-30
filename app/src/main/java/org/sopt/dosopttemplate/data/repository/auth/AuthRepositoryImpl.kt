package org.sopt.dosopttemplate.data.repository.auth

import org.sopt.dosopttemplate.data.entity.auth.AuthInfo
import org.sopt.dosopttemplate.data.service.AuthService
import org.sopt.dosopttemplate.data.source.request.RequestSignInDto
import org.sopt.dosopttemplate.data.source.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.source.response.ResponseSignInDto
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService) :
    AuthRepository {
    override suspend fun postSignUpInfo(
        username: String,
        password: String,
        nickname: String
    ): Result<Unit> = runCatching {
        authService.postSignUpInfo(RequestSignUpDto(username, password, nickname))
    }

    override suspend fun postSignInInfo(
        username: String,
        password: String
    ): Result<AuthInfo> = runCatching {
        convertToAuthInfo(authService.postSignInInfo(RequestSignInDto(username, password)))
    }

    private fun convertToAuthInfo(signInResponse: ResponseSignInDto): AuthInfo {
        return AuthInfo(
            id = signInResponse.id,
            username = signInResponse.username,
            name = signInResponse.nickname,
        )
    }
}

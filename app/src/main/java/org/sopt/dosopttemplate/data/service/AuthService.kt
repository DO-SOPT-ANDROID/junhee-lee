package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.source.request.RequestSignInDto
import org.sopt.dosopttemplate.data.source.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.source.response.ResponseSignInDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    suspend fun postSignUpInfo(
        @Body request: RequestSignUpDto
    ): Response<Unit>

    @POST("api/v1/members/sign-in")
    suspend fun postSignInInfo(
        @Body request: RequestSignInDto
    ) : ResponseSignInDto
}

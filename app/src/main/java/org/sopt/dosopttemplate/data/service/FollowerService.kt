package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.source.response.ResponseFollowerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("api/users/")
    suspend fun getFollowerList(
        @Query("page") page: Int = 2
    ): ResponseFollowerDto

}

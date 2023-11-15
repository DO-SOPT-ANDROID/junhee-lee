package org.sopt.dosopttemplate.data.repository.follower

import org.sopt.dosopttemplate.data.entity.auth.AuthInfo
import org.sopt.dosopttemplate.data.entity.follower.FollowerList
import retrofit2.Response

interface FollowerRepository {

    suspend fun getFollowerList(): Result<FollowerList>

}

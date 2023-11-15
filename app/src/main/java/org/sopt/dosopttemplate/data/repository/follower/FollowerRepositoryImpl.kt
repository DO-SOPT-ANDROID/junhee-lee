package org.sopt.dosopttemplate.data.repository.follower

import org.sopt.dosopttemplate.data.entity.follower.FollowerData
import org.sopt.dosopttemplate.data.entity.follower.FollowerList
import org.sopt.dosopttemplate.data.service.FollowerService
import org.sopt.dosopttemplate.data.source.response.ResponseFollowerDto
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(private val followerService: FollowerService) :
    FollowerRepository {
    override suspend fun getFollowerList(): Result<FollowerList> {
        return runCatching {
            convertToFollowerList(followerService.getFollowerList())
        }.onSuccess { result ->
            Result.success(result)
        }.onFailure { exception ->
            Result.failure<FollowerList>(exception)
        }
    }

    private fun convertToFollowerList(followerListResponse: ResponseFollowerDto.Data): FollowerList {
        val followerDataList = listOf(
            FollowerData(
                avatar = followerListResponse.avatar,
                email = followerListResponse.email,
                firstName = followerListResponse.firstName,
                id = followerListResponse.id,
                lastName = followerListResponse.lastName
            )
        )
        return FollowerList(data = followerDataList)
    }
}

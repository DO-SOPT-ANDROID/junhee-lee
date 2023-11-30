package org.sopt.dosopttemplate.data.repository.follower

import org.sopt.dosopttemplate.data.entity.follower.FollowerData
import org.sopt.dosopttemplate.data.entity.follower.FollowerList
import org.sopt.dosopttemplate.data.service.FollowerService
import org.sopt.dosopttemplate.data.source.response.ResponseFollowerDto
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(private val followerService: FollowerService) :
    FollowerRepository {
    override suspend fun getFollowerList(): Result<FollowerList> = runCatching {
        convertToFollowerList(followerService.getFollowerList())
    }
}

private fun convertToFollowerList(followerListResponse: ResponseFollowerDto): FollowerList {
    val followerDataList = followerListResponse.data.map {
        FollowerData(
            avatar = it.avatar,
            email = it.email,
            firstName = it.firstName,
            id = it.id,
            lastName = it.lastName
        )
    }
    return FollowerList(data = followerDataList)
}


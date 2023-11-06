package org.sopt.dosopttemplate.data.repository.Home

import org.sopt.dosopttemplate.data.entity.home.FriendProfileEntity
import org.sopt.dosopttemplate.data.entity.home.Profile

interface HomeRepository {

    suspend fun getDefaultProfile(): Result<List<Profile>>
    suspend fun getProfileList(): Result<List<FriendProfileEntity>>
    suspend fun addFriend(friend: FriendProfileEntity)
    suspend fun deleteFriendByName(name: String)
}

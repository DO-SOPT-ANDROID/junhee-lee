package org.sopt.dosopttemplate.data.repository.home

import org.sopt.dosopttemplate.data.database.FriendProfileDataBase
import org.sopt.dosopttemplate.data.datasource.local.ProfileDataSource
import org.sopt.dosopttemplate.data.entity.home.FriendProfileEntity
import org.sopt.dosopttemplate.data.entity.home.Profile
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: ProfileDataSource,
    private val friendDataBase: FriendProfileDataBase
) : HomeRepository {
    override suspend fun getDefaultProfile(): Result<List<Profile>> {
        return runCatching {
            val profileList = dataSource.getProfileList()
            profileList
        }.fold(
            onSuccess = {
                Result.success(it)
            },
            onFailure = { exception ->
                Result.failure(exception)
            }
        )
    }

    override suspend fun getProfileList(): Result<List<FriendProfileEntity>> {
        return runCatching {
            val profileList = friendDataBase.dao().getAll()
            profileList
        }.fold(
            onSuccess = {
                Result.success(it)
            },
            onFailure = { exception ->
                Result.failure(exception)
            },
        )
    }

    override suspend fun addFriend(friend: FriendProfileEntity) {
        friendDataBase.dao().add(friend)
    }

    override suspend fun deleteFriendByName(name: String) {
        friendDataBase.dao().deleteProfileById(name)
    }

}

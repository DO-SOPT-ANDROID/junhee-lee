package org.sopt.dosopttemplate.data.repository.Home

import org.sopt.dosopttemplate.data.datasource.local.ProfileDataSource
import org.sopt.dosopttemplate.data.entity.home.Profile
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val profileDataSource: ProfileDataSource) :
    HomeRepository {
    override suspend fun getProfileList(): Result<List<Profile>> {
        return kotlin.runCatching {
            val profileList = profileDataSource.getProfileList()
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

}

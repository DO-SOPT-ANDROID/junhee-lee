package org.sopt.dosopttemplate.data.repository.Home

import org.sopt.dosopttemplate.data.entity.home.Profile

interface HomeRepository {

    suspend fun getProfileList(): Result<List<Profile>>
}

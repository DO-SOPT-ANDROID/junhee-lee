package org.sopt.dosopttemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.datasource.local.DummyProfile
import org.sopt.dosopttemplate.data.datasource.local.ProfileDataSource
import org.sopt.dosopttemplate.data.repository.auth.AuthRepository
import org.sopt.dosopttemplate.data.repository.auth.AuthRepositoryImpl
import org.sopt.dosopttemplate.data.repository.follower.FollowerRepository
import org.sopt.dosopttemplate.data.repository.follower.FollowerRepositoryImpl
import org.sopt.dosopttemplate.data.repository.home.HomeRepository
import org.sopt.dosopttemplate.data.repository.home.HomeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesHomeRepository(homeRepository: HomeRepositoryImpl): HomeRepository =
        homeRepository

    @Provides
    @Singleton
    fun providesProfileDataSource(profileDataSource: DummyProfile): ProfileDataSource =
        profileDataSource

    @Provides
    @Singleton
    fun providesAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository =
        authRepository

    @Provides
    @Singleton
    fun providesFollowerRepository(followerRepository: FollowerRepositoryImpl): FollowerRepository =
        followerRepository
}

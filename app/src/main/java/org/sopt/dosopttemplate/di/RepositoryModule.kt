package org.sopt.dosopttemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.datasource.local.DummyProfile
import org.sopt.dosopttemplate.data.datasource.local.ProfileDataSource
import org.sopt.dosopttemplate.data.repository.Home.HomeRepository
import org.sopt.dosopttemplate.data.repository.Home.HomeRepositoryImpl
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
    fun providesDummyRepository(profileDataSource: DummyProfile): ProfileDataSource =
        profileDataSource
}

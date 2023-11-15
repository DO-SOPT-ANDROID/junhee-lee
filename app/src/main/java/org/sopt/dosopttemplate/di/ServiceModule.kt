package org.sopt.dosopttemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.service.AuthService
import org.sopt.dosopttemplate.data.service.FollowerService
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    private inline fun <reified T> Retrofit.create(): T = this.create(T::class.java)

    @Provides
    @Singleton
    fun provideAuthService(@Named("auth")retrofit: Retrofit): AuthService = retrofit.create()

    @Provides
    @Singleton
    fun provideFollowerService(@Named("reqres")retrofit: Retrofit): FollowerService = retrofit.create()

}

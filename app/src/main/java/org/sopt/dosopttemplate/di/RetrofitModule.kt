package org.sopt.dosopttemplate.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.dosopttemplate.BuildConfig.BASE_URL
import org.sopt.dosopttemplate.BuildConfig.REQRES_BASE_URL
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    },
            ).build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true
        encodeDefaults = true
    }

    @Provides
    @Singleton
    @Named("logger")
    fun provideHttpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    @Provides
    @Singleton
    @Named("auth")
    fun provideAuthOkHttpClient(
        @Named("logger") loggingInterceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor).build()

    @Provides
    @Singleton
    @Named("reqres")
    fun provideReqresOkHttpClient(
        @Named("logger") loggingInterceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor).build()

    @Provides
    @Singleton
    @Named("auth")
    fun provideAuthRetrofit(@Named("auth") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(
            Json.asConverterFactory("application/json".toMediaType()),
        ).build()

    @Provides
    @Singleton
    @Named("reqres")
    fun provideReqresRetrofit(@Named("reqres") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(REQRES_BASE_URL).client(okHttpClient).addConverterFactory(
            Json.asConverterFactory("application/json".toMediaType()),
        ).build()
}


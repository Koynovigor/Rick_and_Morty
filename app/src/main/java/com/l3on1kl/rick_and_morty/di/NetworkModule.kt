package com.l3on1kl.rick_and_morty.di

import com.l3on1kl.rick_and_morty.data.remote.api.CharacterApi
import com.l3on1kl.rick_and_morty.presentation.util.AndroidConnectivityObserver
import com.l3on1kl.rick_and_morty.presentation.util.ConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    @Provides
    @Singleton
    fun provideLogging(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

    @Provides
    @Singleton
    fun provideOkHttp(
        logging: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttp: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()

    @Provides
    @Singleton
    fun provideCharacterApi(
        retrofit: Retrofit
    ): CharacterApi =
        retrofit.create(CharacterApi::class.java)

    @Provides
    @Singleton
    fun provideConnectivityObserver(
        impl: AndroidConnectivityObserver
    ): ConnectivityObserver = impl
}

package com.example.demopetapp.di

import com.example.demopetapp.data.remote.authentication.RefreshTokenInterceptor
import com.example.demopetapp.data.remote.authentication.TokenAuthenticator
import com.example.demopetapp.data.remote.service.PetFinderService
import com.example.demopetapp.data.remote.service.TokenService
import com.example.demopetapp.data.repository.TokenRepositoryImpl
import com.example.demopetapp.domain.manager.AccessTokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_API_URL = "https://api.petfinder.com/v2/"

    @Provides
    @Singleton
    fun getDefaultOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient.Builder = OkHttpClient().newBuilder().addInterceptor(loggingInterceptor)

    @Provides
    @Singleton
    fun getDefaultRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())

    @Singleton
    @Provides
    fun getTokenService(
        defaultRetrofitBuilder: Retrofit.Builder,
        defaultOkHttpClientBuilder: OkHttpClient.Builder
    ): TokenService {
        return defaultRetrofitBuilder
            .client(defaultOkHttpClientBuilder.build())
            .build()
            .create(TokenService::class.java)
    }

    @Provides
    @Singleton
    fun getPetFinderService(
        defaultRetrofitBuilder: Retrofit.Builder,
        defaultOkHttpClientBuilder: OkHttpClient.Builder,
        refreshTokenInterceptor: RefreshTokenInterceptor,
        petFinderAuthenticator: TokenAuthenticator
    ): PetFinderService {
        return defaultRetrofitBuilder
            .client(
                defaultOkHttpClientBuilder
                    .addInterceptor(refreshTokenInterceptor)
                    .authenticator(petFinderAuthenticator)
                    .build()
            )
            .build()
            .create(PetFinderService::class.java)
    }

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun getRefreshTokenInterceptor(tokenManager: AccessTokenManager): RefreshTokenInterceptor =
        RefreshTokenInterceptor(tokenManager)

    @Provides
    @Singleton
    fun getPetFinderAuthenticator(
        tokenService: TokenService,
        tokenManager: AccessTokenManager
    ): TokenAuthenticator = TokenAuthenticator(
        TokenRepositoryImpl(tokenService, tokenManager)
    )
}

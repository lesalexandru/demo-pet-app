package com.example.demopetapp.data.remote.service

import com.example.demopetapp.BuildConfig
import com.example.demopetapp.data.remote.dto.TokenDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

private const val GRANT_TYPE_VALUE = "client_credentials"

interface TokenService {

    @FormUrlEncoded
    @POST("oauth2/token")
    suspend fun getAuthToken(
        @Field("grant_type") grantType: String = GRANT_TYPE_VALUE,
        @Field("client_id") clientId: String = BuildConfig.API_KEY,
        @Field("client_secret") clientSecret: String = BuildConfig.API_SECRET
    ): TokenDto
}

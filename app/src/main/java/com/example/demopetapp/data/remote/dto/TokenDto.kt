package com.example.demopetapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TokenDto(
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("expires_in")
    val expiresIn: Long,
    @SerializedName("access_token")
    val accessToken: String
)

package com.example.demopetapp.domain.manager

interface AccessTokenManager {

    suspend fun getCurrentToken(): String?

    suspend fun setToken(token: String)
}

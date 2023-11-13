package com.example.demopetapp.domain.repository

interface TokenRepository {

    suspend fun getToken(): String?

    suspend fun refreshAndGetToken(): String
}

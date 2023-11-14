package com.example.demopetapp.data.repository

import com.example.demopetapp.data.remote.service.TokenService
import com.example.demopetapp.domain.manager.AccessTokenManager
import com.example.demopetapp.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenService: TokenService,
    private val tokenManager: AccessTokenManager
) : TokenRepository {

    override suspend fun getToken() = tokenManager.getCurrentToken()

    override suspend fun refreshAndGetToken(): String =
        tokenService.getAuthToken().accessToken.also { refreshedToken ->
            tokenManager.setToken(refreshedToken)
        }
}

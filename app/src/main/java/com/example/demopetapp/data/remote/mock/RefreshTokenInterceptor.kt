package com.example.demopetapp.data.remote.mock

import com.example.demopetapp.data.remote.withAuthHeader
import com.example.demopetapp.domain.manager.AccessTokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class RefreshTokenInterceptor(private val tokenManager: AccessTokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentToken = runBlocking { tokenManager.getCurrentToken() }

        return chain.proceed(chain.request().apply {
            if (currentToken != null) withAuthHeader(currentToken)
        })
    }
}

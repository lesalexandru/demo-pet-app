package com.example.demopetapp.data.remote.authentication

import com.example.demopetapp.domain.repository.TokenRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val tokenRepository: TokenRepository
) : Authenticator {

    private val mutex = Mutex()
    override fun authenticate(route: Route?, response: Response): Request? {
        try {
            return runBlocking {
                val token: String? = tokenRepository.getToken()
                return@runBlocking mutex.withLock {
                    val newToken = tokenRepository.getToken()
                    if (newToken != null && token != newToken) {
                        // It was refreshed, so go ahead and just make the call
                        return@withLock response.request.withAuthHeader(newToken)
                    }

                    // Still same old token, so fetch new one
                    val refreshedToken = tokenRepository.refreshAndGetToken()
                    return@withLock response.request.withAuthHeader(refreshedToken)
                }
            }
        } catch (e: Exception) {
            return null
        }
    }

}

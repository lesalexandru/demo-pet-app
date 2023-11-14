package com.example.demopetapp.data.remote.authentication

import okhttp3.Request

private const val AUTH_HEADER = "Authorization"

fun Request.withAuthHeader(token: String): Request = newBuilder()
    .header(AUTH_HEADER, "Bearer $token")
    .build()
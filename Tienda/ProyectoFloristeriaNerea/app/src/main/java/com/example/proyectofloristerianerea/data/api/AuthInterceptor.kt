package com.example.proyectofloristerianerea.data.api

import com.example.proyectofloristerianerea.domain.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        SessionManager.token?.let { token: String ->
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}
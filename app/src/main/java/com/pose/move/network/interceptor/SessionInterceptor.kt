package com.pose.move.network.interceptor

import com.pose.move.data.preference.AuthTokenProvider
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class SessionInterceptor(
    private val authTokenProvider: AuthTokenProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val token = authTokenProvider.getToken()
        if (token.isNotEmpty()) {
            builder.header(AUTHORIZATION_KEY, AUTHORIZATION_BEARER_PREFIX + token)
        }
        val response = chain.proceed(builder.build())

        if (response.code == HTTP_SESSION_EXPIRED_CODE) {
            //TODO: Session expired, logout user
        }

        return response
    }

    companion object {
        private const val AUTHORIZATION_KEY = "Authorization"
        private const val AUTHORIZATION_BEARER_PREFIX = "Bearer "
        private const val HTTP_SESSION_EXPIRED_CODE = 402
    }
}
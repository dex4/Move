package com.pose.move.data.preference

interface AuthTokenProvider {

    suspend fun getToken(): String
}
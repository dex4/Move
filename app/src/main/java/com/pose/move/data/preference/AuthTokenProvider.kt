package com.pose.move.data.preference

interface AuthTokenProvider {

    fun getToken(): String
}
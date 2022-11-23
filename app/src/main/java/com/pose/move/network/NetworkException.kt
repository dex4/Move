package com.pose.move.network

import java.io.IOException

sealed class NetworkException : IOException() {

    data class ApiException(
        val code: Int,
        override val message: String
    ) : NetworkException()

    data class UnknownException(
        override val message: String? = null
    ) : NetworkException()

    object IoException : NetworkException()
}
package com.pose.move.network

sealed interface ApiResponse<out T> {

    data class Success<T>(
        val value: T
    ) : ApiResponse<T>

    sealed interface Error : ApiResponse<Nothing> {

        data class ApiException(
            val code: Int,
            val message: String
        ) : Error

        data class UnknownException(
            val message: String? = null
        ) : Error

        object NetworkError : Error
    }
}
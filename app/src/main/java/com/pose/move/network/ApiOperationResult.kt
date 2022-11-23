package com.pose.move.network

sealed interface ApiOperationResult<out T> {

    data class Success<T>(
        val value: T
    ) : ApiOperationResult<T>

    sealed interface Error : ApiOperationResult<Nothing> {

        data class ApiError(
            val code: Int,
            val message: String
        ) : Error

        data class UnknownError(
            val message: String? = null
        ) : Error

        object NetworkError : Error
    }

    companion object {
        suspend inline operator fun <T> invoke(crossinline apiOperation: suspend () -> T): ApiOperationResult<T> =
            try {
                Success(apiOperation())
            } catch (e: NetworkException) {
                when (e) {
                    is NetworkException.ApiException -> Error.ApiError(e.code, e.message)
                    is NetworkException.UnknownException -> Error.UnknownError(e.message)
                    NetworkException.IoException -> Error.NetworkError
                }
            }
    }
}
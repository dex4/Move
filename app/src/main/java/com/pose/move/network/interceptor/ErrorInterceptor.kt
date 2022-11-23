package com.pose.move.network.interceptor

import android.util.Log
import com.pose.move.network.ErrorResponse
import com.pose.move.network.ErrorResponseJsonAdapter
import com.pose.move.network.NetworkException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.IOException
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.HttpException

class ErrorInterceptor @Inject constructor(
    moshi: Moshi
) : Interceptor {

    private val errorJsonAdapter: JsonAdapter<ErrorResponse> = ErrorResponseJsonAdapter(moshi)

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            return chain.proceed(chain.request())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val source = throwable.response()?.errorBody()?.source() ?: throw NetworkException.UnknownException()
                    val errorResponse = errorJsonAdapter.fromJson(source) ?: throw NetworkException.UnknownException()

                    throw NetworkException.ApiException(throwable.code(), errorResponse.message)
                }
                is IOException -> throw NetworkException.IoException
                else -> {
                    val message = throwable.message.toString()
                    Log.d("ErrorInterceptor", message)
                    throw NetworkException.UnknownException(message)
                }
            }
        }
    }
}
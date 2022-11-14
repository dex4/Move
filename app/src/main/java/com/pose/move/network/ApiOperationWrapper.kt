package com.pose.move.network

import android.util.Log
import com.squareup.moshi.Moshi
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

class ApiOperationWrapper @Inject constructor(moshi: Moshi) {

    val errorJsonAdapter: ErrorResponseJsonAdapter = ErrorResponseJsonAdapter(moshi)

    suspend inline operator fun <T> invoke(
        coroutineContext: CoroutineContext = IO,
        crossinline operation: suspend () -> T
    ): ApiResponse<T> =
        withContext(coroutineContext) {
            try {
                ApiResponse.Success(operation())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        val source =
                            throwable.response()?.errorBody()?.source() ?: return@withContext ApiResponse.Error.UnknownException()
                        val errorResponse =
                            errorJsonAdapter.fromJson(source) ?: return@withContext ApiResponse.Error.UnknownException()
                        val code = throwable.code()
                        ApiResponse.Error.ApiException(code, errorResponse.message)
                    }
                    is IOException -> ApiResponse.Error.NetworkError
                    else -> {
                        val message = throwable.message.toString()
                        Log.d("ApiOperationWrapper", message)
                        ApiResponse.Error.UnknownException(message)
                    }
                }
            }
        }
}
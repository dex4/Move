package com.pose.move.network.mock

import com.pose.move.network.ErrorResponse
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol.HTTP_2
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

fun createErrorResponse(message: String, code: Int): Response<ErrorResponse> =
    Response.error(
        code,
        "{\"message\":\"$message\", \"code\": $code}".toResponseBody("application/json".toMediaType())
    )

fun Interceptor.Chain.createSuccessResponse(responseBody: ResponseBody): okhttp3.Response =
    okhttp3.Response.Builder()
        .protocol(HTTP_2)
        .request(request())
        .code(200)
        .message("OK")
        .body(responseBody)
        .build()
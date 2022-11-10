package com.pose.move.network.mock

import com.pose.move.network.ErrorResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

fun createErrorResponse(message: String, code: Int): Response<ErrorResponse> =
    Response.error(
        code,
        "{\"message\":\"$message\", \"code\": $code}".toResponseBody("application/json".toMediaType())
    )
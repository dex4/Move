package com.pose.move.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "message") val message: String,
    @Json(name = "code") val code: Int
)
package com.pose.move.network.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "userId") val userId: String,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "hasAddedProfilePicture") val hasAddedProfilePicture: Boolean
)

@JsonClass(generateAdapter = true)
data class RegisterResponse(
    @Json(name = "user") val user: UserDto,
    @Json(name = "authToken") val authToken: String
)

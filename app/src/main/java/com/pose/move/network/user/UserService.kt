package com.pose.move.network.user

import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): RegisterResponse
}
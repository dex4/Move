package com.pose.move.network.user

import com.pose.move.network.ApiOperationResult
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val userService: UserService) {

    suspend fun registerUser(userName: String, email: String, password: String): ApiOperationResult<RegisterResponse> =
        ApiOperationResult {
            userService.registerUser(RegisterRequest(userName, email, password))
        }
}
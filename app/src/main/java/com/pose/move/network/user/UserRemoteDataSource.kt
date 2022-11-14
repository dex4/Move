package com.pose.move.network.user

import com.pose.move.network.ApiOperationWrapper
import com.pose.move.network.ApiResponse
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userService: UserService,
    private val apiOperationWrapper: ApiOperationWrapper
) {

    suspend fun registerUser(userName: String, email: String, password: String): ApiResponse<RegisterResponse> =
        apiOperationWrapper {
            userService.registerUser(RegisterRequest(userName, email, password))
        }
}
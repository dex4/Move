package com.pose.move.network.mock

import com.pose.move.network.user.UserDto
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

class MockServer(
    private val userServiceRequestHandler: UserServiceRequestHandler
) {

    private val users = mutableMapOf<String, StoredUser>()

    fun handleRequest(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url.toUri().toString()

        return when {
            requestUrl.contains("register") -> {

                val registerResult = userServiceRequestHandler.registerUser(chain, request, users)
                users[registerResult.value.authToken] = StoredUser(registerResult.value.user, registerResult.value.password)

                registerResult.response
            }
            else -> throw IOException()
        }
    }
}

data class StoredUser(
    val userDto: UserDto,
    val password: String
)
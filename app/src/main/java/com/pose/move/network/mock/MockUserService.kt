package com.pose.move.network.mock

import com.pose.move.network.user.RegisterRequest
import com.pose.move.network.user.RegisterResponse
import com.pose.move.network.user.UserDto
import com.pose.move.network.user.UserService
import java.util.UUID
import kotlinx.coroutines.delay
import retrofit2.HttpException
import retrofit2.mock.BehaviorDelegate

class MockUserService(
    private val behaviorDelegate: BehaviorDelegate<UserService>
) : UserService {

    private val users = mutableMapOf<String, StoredUser>()

    override suspend fun registerUser(registerRequest: RegisterRequest): RegisterResponse {
        delay(1500L)
        validateRequest(registerRequest.email, registerRequest.password)

        val user = UserDto(UUID.randomUUID().toString(), registerRequest.name, registerRequest.email, false)
        users[user.userId] = StoredUser(user, registerRequest.password)

        return behaviorDelegate.returningResponse(RegisterResponse(user, UUID.randomUUID().toString())).registerUser(registerRequest)
    }

    @Throws(HttpException::class)
    private fun validateRequest(email: String, password: String) {
        if (users.any { it.value.userDto.email == email }) {
            throw HttpException(createErrorResponse("User email already registered.", 402))
        }

        if (password.length < 3) {
            throw HttpException(createErrorResponse("Password is too short.", 402))
        }
    }
}

private data class StoredUser(
    val userDto: UserDto,
    val password: String
)
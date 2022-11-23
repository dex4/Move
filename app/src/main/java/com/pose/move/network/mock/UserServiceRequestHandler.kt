package com.pose.move.network.mock

import com.pose.move.network.user.RegisterRequest
import com.pose.move.network.user.RegisterRequestJsonAdapter
import com.pose.move.network.user.RegisterResponse
import com.pose.move.network.user.RegisterResponseJsonAdapter
import com.pose.move.network.user.UserDto
import com.squareup.moshi.Moshi
import java.util.UUID
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import retrofit2.HttpException

class UserServiceRequestHandler(
    moshi: Moshi
) {

    private val registerRequestJsonAdapter = RegisterRequestJsonAdapter(moshi)
    private val registerResponseJsonAdapter = RegisterResponseJsonAdapter(moshi)

    fun registerUser(
        chain: Interceptor.Chain,
        request: Request,
        users: Map<String, StoredUser>
    ): ServiceRequestHandlerResult<RegisterResultValue> {
        val requestBuffer = Buffer()
        request.body?.writeTo(requestBuffer)

        val registerRequestBody = registerRequestJsonAdapter.fromJson(requestBuffer)
        val user = validateRegisterRequest(registerRequestBody, users)
        val registerResponse = RegisterResponse(user, UUID.randomUUID().toString())

        return ServiceRequestHandlerResult(
            chain.createSuccessResponse(registerResponseJsonAdapter.toJson(registerResponse).toResponseBody()),
            RegisterResultValue(user, registerResponse.authToken, registerRequestBody!!.password)
        )
    }

    private fun validateRegisterRequest(registerRequest: RegisterRequest?, users: Map<String, StoredUser>): UserDto {
        registerRequest ?: throw HttpException(createErrorResponse("Missing request body.", 403))

        if (users.any { it.value.userDto.email == registerRequest.email }) {
            throw HttpException(createErrorResponse("User email already registered.", 403))
        }

        if (registerRequest.password.length < 3) {
            throw HttpException(createErrorResponse("Password is too short.", 403))
        }

        return UserDto(UUID.randomUUID().toString(), registerRequest.name, registerRequest.email, false)
    }
}

data class RegisterResultValue(
    val user: UserDto,
    val authToken: String,
    val password: String
)

data class ServiceRequestHandlerResult<out T>(
    val response: Response,
    val value: T
)
package com.pose.move.data.preference

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class RuntimeAuthTokenProvider(
    private val internalStorageManager: InternalStorageManager
) : AuthTokenProvider {

    override suspend fun getToken(): String =
        internalStorageManager.authenticationToken.first()
}
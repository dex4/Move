package com.pose.move.data.preference

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class RuntimeAuthTokenProvider(
    private val internalStorageManager: InternalStorageManager
) : AuthTokenProvider {

    override fun getToken(): String = runBlocking {
        internalStorageManager.authenticationToken.first()
    }
}
package com.pose.move.network.mock

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class MockServerCallInterceptor(
    private val mockServer: MockServer,
    private val ioDispatcher: CoroutineDispatcher
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        runBlocking(ioDispatcher) {
            delay(1500L)
            mockServer.handleRequest(chain)
        }
}
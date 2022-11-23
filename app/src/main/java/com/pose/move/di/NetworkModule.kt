package com.pose.move.di

import com.pose.move.data.preference.AuthTokenProvider
import com.pose.move.data.preference.InternalStorageManager
import com.pose.move.data.preference.RuntimeAuthTokenProvider
import com.pose.move.network.interceptor.ErrorInterceptor
import com.pose.move.network.interceptor.SessionInterceptor
import com.pose.move.network.mock.MockServer
import com.pose.move.network.mock.MockServerCallInterceptor
import com.pose.move.network.mock.UserServiceRequestHandler
import com.pose.move.network.user.UserService
import com.pose.move.util.dispatchers.Dispatcher
import com.pose.move.util.dispatchers.MoveDispatchers
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://localhost")
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        sessionInterceptor: SessionInterceptor,
        errorInterceptor: ErrorInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        mockServerCallInterceptor: MockServerCallInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(sessionInterceptor)
            .addInterceptor(errorInterceptor)
            .addInterceptor(mockServerCallInterceptor)
            .build()


    @Singleton
    @Provides
    fun provideMockServerCallInterceptor(
        mockServer: MockServer,
        @Dispatcher(MoveDispatchers.IO) ioDispatcher: CoroutineDispatcher
    ) = MockServerCallInterceptor(mockServer, ioDispatcher)

    @Singleton
    @Provides
    fun provideSessionInterceptor(authTokenProvider: AuthTokenProvider): SessionInterceptor =
        SessionInterceptor(authTokenProvider)

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideErrorInterceptor(moshi: Moshi): ErrorInterceptor =
        ErrorInterceptor(moshi)

    @Singleton
    @Provides
    fun provideAuthTokenProvider(internalStorageManager: InternalStorageManager): AuthTokenProvider =
        RuntimeAuthTokenProvider(internalStorageManager)

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun provideMockServer(userServiceRequestHandler: UserServiceRequestHandler): MockServer = MockServer(userServiceRequestHandler)

    @Singleton
    @Provides
    fun provideUserServiceRequestHandler(moshi: Moshi): UserServiceRequestHandler = UserServiceRequestHandler(moshi)
}
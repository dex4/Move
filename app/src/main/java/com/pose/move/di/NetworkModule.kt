package com.pose.move.di

import com.pose.move.data.preference.AuthTokenProvider
import com.pose.move.data.preference.InternalStorageManager
import com.pose.move.data.preference.RuntimeAuthTokenProvider
import com.pose.move.network.interceptor.SessionInterceptor
import com.pose.move.network.mock.MockUserService
import com.pose.move.network.user.UserService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://www.tapptitude.com")
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(sessionInterceptor: SessionInterceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(sessionInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideSessionInterceptor(authTokenProvider: AuthTokenProvider): SessionInterceptor =
        SessionInterceptor(authTokenProvider)

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Singleton
    @Provides
    fun provideAuthTokenProvider(internalStorageManager: InternalStorageManager): AuthTokenProvider =
        RuntimeAuthTokenProvider(internalStorageManager)

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideUserService(retrofit: MockRetrofit): UserService = MockUserService(retrofit.create(UserService::class.java))

    @Singleton
    @Provides
    fun provideMockRetrofit(retrofit: Retrofit): MockRetrofit =
        MockRetrofit.Builder(retrofit).networkBehavior(NetworkBehavior.create()).build()
}
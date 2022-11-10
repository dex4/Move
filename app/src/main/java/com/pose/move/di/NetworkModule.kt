package com.pose.move.di

import com.pose.move.network.mock.MockUserService
import com.pose.move.network.user.UserService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
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
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()

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
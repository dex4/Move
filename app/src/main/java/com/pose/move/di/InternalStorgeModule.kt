package com.pose.move.di

import android.content.Context
import com.pose.move.data.preference.InternalStorageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object InternalStorageModule {

    @Provides
    @Singleton
    fun provideInternalStorageManager(@ApplicationContext context: Context): InternalStorageManager {
        return InternalStorageManager(context)
    }
}
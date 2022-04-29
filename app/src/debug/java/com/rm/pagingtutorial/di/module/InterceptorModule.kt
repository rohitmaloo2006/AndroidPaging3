package com.rm.pagingtutorial.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {

    companion object {
        val LOG_LEVEL = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideApiInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = LOG_LEVEL
    }
}
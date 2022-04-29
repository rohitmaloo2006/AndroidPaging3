package com.rm.pagingtutorial.di.module

import com.rm.pagingtutorial.common.Constants
import com.rm.pagingtutorial.data.remote.PassengerApi
import com.rm.pagingtutorial.data.repository.PassengerRepositoryImpl
import com.rm.pagingtutorial.domain.repository.PassengerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [InterceptorModule::class])
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient().newBuilder().run {
            addInterceptor(interceptor)
        }.build()

    @Provides
    @Singleton
    fun provideStockApi(httpClient: OkHttpClient): PassengerApi =
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(httpClient).build()
            .create(PassengerApi::class.java)

    @Provides
    @Singleton
    fun providePassengerRepository(api: PassengerApi): PassengerRepository {
        return PassengerRepositoryImpl(api)
    }

}
package com.rm.pagingtutorial.data.remote

import com.rm.pagingtutorial.data.dto.PassengerDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface PassengerApi {

    @GET("v1/passenger")
    suspend fun getPassengers(
        @Query("page") page: Int,
        @Query("size") size: Int = 10
    ): PassengerDTO
}
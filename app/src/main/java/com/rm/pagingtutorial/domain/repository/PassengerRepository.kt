package com.rm.pagingtutorial.domain.repository

import androidx.paging.PagingData
import com.rm.pagingtutorial.data.dto.Data
import kotlinx.coroutines.flow.Flow

interface PassengerRepository {
    suspend fun loadPassenger(): Flow<PagingData<Data>>
}
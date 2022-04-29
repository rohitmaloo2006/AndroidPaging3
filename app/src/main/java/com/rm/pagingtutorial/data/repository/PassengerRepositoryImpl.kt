package com.rm.pagingtutorial.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rm.pagingtutorial.data.dto.Data
import com.rm.pagingtutorial.data.remote.PassengerApi
import com.rm.pagingtutorial.domain.repository.PassengerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PassengerRepositoryImpl @Inject constructor(private val api: PassengerApi) :
    PassengerRepository {
    override suspend fun loadPassenger(): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PassengerDataSource(api) }
        ).flow
    }
}
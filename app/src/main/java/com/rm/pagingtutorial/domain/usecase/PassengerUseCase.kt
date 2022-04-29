package com.rm.pagingtutorial.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.rm.pagingtutorial.data.mapper.PassengerMapper
import com.rm.pagingtutorial.domain.model.Passenger
import com.rm.pagingtutorial.domain.repository.PassengerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PassengerUseCase @Inject constructor(private val passengerRepository: PassengerRepository) {
    suspend fun execute(): Flow<PagingData<Passenger>> {
        return passengerRepository.loadPassenger().map { pagingData ->
            pagingData.map { data ->
                PassengerMapper().convert(data)
            }
        }.flowOn(Dispatchers.IO)
    }
}
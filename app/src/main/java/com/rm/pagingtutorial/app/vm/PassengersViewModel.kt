package com.rm.pagingtutorial.app.vm

import androidx.lifecycle.ViewModel
import androidx.paging.map
import com.rm.pagingtutorial.domain.model.Passenger
import com.rm.pagingtutorial.domain.usecase.PassengerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PassengersViewModel @Inject constructor(
    private var useCase: PassengerUseCase
) : ViewModel() {

    private val _passengerList = MutableStateFlow(Passenger())
    val passengerList: StateFlow<Passenger> = _passengerList

    fun getPassengerData() = useCase.execute().map { pagingData ->
        pagingData.map {
            it
        }
    }.flowOn(Dispatchers.IO)
}
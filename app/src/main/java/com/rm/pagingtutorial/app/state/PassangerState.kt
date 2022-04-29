package com.rm.pagingtutorial.app.state

import com.rm.pagingtutorial.domain.model.Passenger

data class PassangerState(
    val isLoading: Boolean = false,
    val passanger: Passenger? = null,
    val error: String? = ""
)

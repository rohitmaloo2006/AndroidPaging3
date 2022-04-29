package com.rm.pagingtutorial.data.dto

data class PassengerDTO(
    val `data`: List<Data>,
    val totalPages: Int,
    val totalPassengers: Int
)
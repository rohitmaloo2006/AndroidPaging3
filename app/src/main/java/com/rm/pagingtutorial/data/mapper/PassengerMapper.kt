package com.rm.pagingtutorial.data.mapper

import com.rm.pagingtutorial.data.dto.Data
import com.rm.pagingtutorial.domain.model.Passenger

class PassengerMapper : DomainModelConverter<Data, Passenger> {
    override fun convert(data: Data): Passenger {
        return Passenger(
            data._id,
            data.airline[0].logo,
            data.trips,
            data.name,
            data.airline[0].head_quaters
        )
    }
}
package com.example.OOBootcamp

import com.example.OOBootcamp.exception.InvalidTicketException
import com.example.OOBootcamp.model.Car
import com.example.OOBootcamp.model.Ticket
import com.example.OOBootcamp.parkingstrategy.ParkingOrderStrategy

class ParkingBoy(
    private val parkingLots: List<ParkingLot>,
    private val parkingOrderStrategy: ParkingOrderStrategy
) {
    fun park(car: Car): Ticket {
        return parkingOrderStrategy.findAvailableParkingLot(parkingLots).park(car)
    }

    fun pickup(ticket: Ticket): Car {
        return parkingLots.find { it.canFindCarBy(ticket) }?.pickup(ticket) ?: throw InvalidTicketException()
    }
}

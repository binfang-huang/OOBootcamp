package com.example.OOBootcamp

import com.example.OOBootcamp.exception.InvalidTicketException
import com.example.OOBootcamp.exception.ParkingLotFullException
import com.example.OOBootcamp.model.Car
import com.example.OOBootcamp.model.Ticket

abstract class ParkingBoy(private val parkingLots: List<ParkingLot>) {


    fun park(car: Car): Ticket {
        val park = findAvailableParkingLot(parkingLots)?.park(car)
        return park ?: throw ParkingLotFullException()
    }

    internal abstract fun findAvailableParkingLot(parkingLots: List<ParkingLot>): ParkingLot?

    fun pickup(ticket: Ticket): Car {
        return parkingLots.find { it.canFindCarBy(ticket) }?.pickup(ticket) ?: throw InvalidTicketException()
    }
}

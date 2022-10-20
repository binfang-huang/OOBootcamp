package com.example.OOBootcamp

import com.example.OOBootcamp.exception.InvalidTicketException
import com.example.OOBootcamp.exception.ParkingLotFullException

class ParkingBoy {
    private val parkingLots = mutableListOf<ParkingLot>()

    fun addParkingLot(parkingLot: ParkingLot) {
        parkingLots.add(parkingLot)
    }

    fun park(car: Car): Ticket {
        val find = parkingLots.maxByOrNull { it.remainingCapacity() } ?: throw ParkingLotFullException()
        return find.park(car)
    }

    fun pickup(ticket: Ticket): Car {
        val parkingLot = parkingLots.find { it.containsTicket(ticket) } ?: throw InvalidTicketException()
        return parkingLot.pickup(ticket)
    }
}

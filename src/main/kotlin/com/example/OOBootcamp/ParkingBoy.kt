package com.example.OOBootcamp

import com.example.OOBootcamp.exception.InvalidTicketException
import com.example.OOBootcamp.exception.ParkingLotFullException

class ParkingBoy(private val parkingLots: List<ParkingLot>) {
    fun park(car: Car): Ticket {
        return parkingLots.filter { !it.isFull() }.maxByOrNull { it.remainingCapacity() }?.park(car) ?: throw ParkingLotFullException()
    }

    fun pickup(ticket: Ticket): Car {
        val parkingLot = parkingLots.find { it.containsTicket(ticket) } ?: throw InvalidTicketException()
        return parkingLot.pickup(ticket)
    }
}

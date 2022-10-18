package com.example.OOBootcamp

import com.example.OOBootcamp.exception.ParkFailedException
import com.example.OOBootcamp.exception.UnparkFailedException
import java.util.UUID

class ParkingLot(val capacity: Int) {
    private val tickets = mutableListOf<ParkingTicket>()

    fun park(cardNumber: String): ParkingTicket {
        if (tickets.size >= capacity) {
            throw ParkFailedException("Parking lot is full")
        }
        val ticket = ParkingTicket(UUID.randomUUID().toString(), cardNumber)
        tickets.add(ticket)
        return ticket
    }

    fun unpark(ticket: ParkingTicket): String {
        if (!tickets.contains(ticket)) {
            throw UnparkFailedException("Ticket is invalid")
        }
        tickets.remove(ticket)
        return ticket.carNumber
    }
}

data class ParkingTicket(val id: String, val carNumber: String)

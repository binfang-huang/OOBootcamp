package com.example.OOBootcamp

import com.example.OOBootcamp.exception.ParkingLotFullException
import com.example.OOBootcamp.exception.InvalidTicketException
import java.util.UUID

class ParkingLot(val capacity: Int) {
    private val tickets = mutableListOf<Ticket>()

    fun park(car: Car): Ticket {
        if (tickets.size >= capacity) {
            throw ParkingLotFullException()
        }
        val ticket = Ticket(car)
        tickets.add(ticket)
        return ticket
    }

    fun pickup(ticket: Ticket): Car {
        if (!tickets.contains(ticket)) {
            throw InvalidTicketException()
        }
        tickets.remove(ticket)
        return ticket.car
    }
}



package com.example.OOBootcamp

import com.example.OOBootcamp.exception.ParkingLotFullException
import com.example.OOBootcamp.exception.InvalidTicketException

class ParkingLot(private val capacity: Int) {
    private val ticketCarMapping = mutableMapOf<Ticket, Car>()

    fun park(car: Car): Ticket {
        if (ticketCarMapping.size >= capacity) {
            throw ParkingLotFullException()
        }
        val ticket = Ticket()
        ticketCarMapping.put(ticket, car)
        return ticket
    }

    fun pickup(ticket: Ticket): Car {
        if (!ticketCarMapping.contains(ticket)) {
            throw InvalidTicketException()
        }
        return ticketCarMapping.remove(ticket)!!
    }

    internal fun isFull(): Boolean {
        return capacity == ticketCarMapping.size
    }

    internal fun containsTicket(ticket: Ticket): Boolean {
        return ticketCarMapping.containsKey(ticket)
    }

    internal fun remainingCapacity(): Int {
        return capacity - ticketCarMapping.size
    }
}



package com.example.OOBootcamp

import com.example.OOBootcamp.exception.ParkFailedException
import java.util.*

class ParkingLot(val capacity: Int) {
    private val tickets = mutableListOf<ParkingTicket>()

    fun park(cardNumber: String): ParkingTicket {
        if(tickets.size>=capacity){
            throw ParkFailedException("Parking lot is full")
        }
        val ticket = ParkingTicket(UUID.randomUUID().toString(), cardNumber)
        tickets.add(ticket)
        return ticket
    }
}

data class ParkingTicket(val id: String, val carNumber: String)

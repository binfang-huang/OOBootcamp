package com.example.OOBootcamp.parkingstrategy

import com.example.OOBootcamp.ParkingLot
import com.example.OOBootcamp.exception.ParkingLotFullException

class SequentialParkingOrderStrategy : ParkingOrderStrategy {
    override fun findAvailableParkingLot(parkingLots: List<ParkingLot>): ParkingLot {
        try {
            return parkingLots.first { !it.isFull() }
        } catch (ex: NoSuchElementException) {
            throw ParkingLotFullException()
        }
    }
}
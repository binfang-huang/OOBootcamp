package com.example.OOBootcamp.parkingstrategy

import com.example.OOBootcamp.ParkingLot
import com.example.OOBootcamp.exception.ParkingLotFullException

class MaxAvailableRateParkingOrderStrategy : ParkingOrderStrategy {
    override fun findAvailableParkingLot(parkingLots: List<ParkingLot>): ParkingLot {
        return parkingLots.filter { !it.isFull() }.maxByOrNull { it.remainingCapacityRate() }
            ?: throw ParkingLotFullException()
    }
}

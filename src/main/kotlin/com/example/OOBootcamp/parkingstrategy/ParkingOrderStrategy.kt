package com.example.OOBootcamp.parkingstrategy

import com.example.OOBootcamp.ParkingLot

interface ParkingOrderStrategy {
    fun findAvailableParkingLot(parkingLots: List<ParkingLot>): ParkingLot
}
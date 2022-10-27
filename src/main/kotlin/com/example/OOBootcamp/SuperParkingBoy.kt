package com.example.OOBootcamp

class SuperParkingBoy(parkingLots: List<ParkingLot>) : ParkingBoy(parkingLots) {
    override fun findAvailableParkingLot(parkingLots: List<ParkingLot>): ParkingLot? {
        return parkingLots.filter { it.isAvailable() }.maxByOrNull { it.remainingCapacityRate() }
    }
}

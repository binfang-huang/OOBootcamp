package com.example.OOBootcamp

class GraduateParkingBoy(parkingLots: List<ParkingLot>) : ParkingBoy(parkingLots) {
    override fun findAvailableParkingLot(parkingLots: List<ParkingLot>): ParkingLot? {
        return parkingLots.find { it.isAvailable() }
    }

}

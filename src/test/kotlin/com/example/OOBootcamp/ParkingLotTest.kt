package com.example.OOBootcamp

import com.example.OOBootcamp.exception.ParkFailedException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ParkingLotTest {
    @Test
    internal fun can_park_successfully_when_parkingLot_has_capacity() {
        val parkingLot = ParkingLot(2)
        val ticket = parkingLot.park("川A55555")
        Assertions.assertEquals("川A55555", ticket.carNumber)
    }

    @Test
    internal fun park_failed_when_parkingLot_is_full() {
        val parkingLot = ParkingLot(2)
        parkingLot.park("川A33333")
        parkingLot.park("川A44444")
        val exception = Assertions.assertThrows(ParkFailedException::class.java) {
            parkingLot.park("川A55555")
        }
        Assertions.assertEquals("Parking lot is full", exception.message)
    }
}
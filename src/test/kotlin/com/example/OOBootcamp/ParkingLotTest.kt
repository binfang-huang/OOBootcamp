package com.example.OOBootcamp

import com.example.OOBootcamp.exception.ParkFailedException
import com.example.OOBootcamp.exception.UnparkFailedException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
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

    @Test
    internal fun unpark_successfully() {
        val parkingLot = ParkingLot(2)
        val ticket = parkingLot.park("川A33333")

        val carNumber = parkingLot.unpark(ticket)

        Assertions.assertEquals("川A33333", carNumber)
    }

    @Nested
    @DisplayName("unpark_failed")
    inner class UnparkFailed {
        @Test
        internal fun unparking_with_fake_ticket_will_fail() {
            val parkingLot = ParkingLot(2)
            parkingLot.park("川A33333")
            val fakeTicket = ParkingTicket("fake", "川A33333")

            val exception = Assertions.assertThrows(UnparkFailedException::class.java) {
                parkingLot.unpark(fakeTicket)
            }

            Assertions.assertEquals("Ticket is invalid", exception.message)
        }

        @Test
        internal fun unparking_with_ticket_twice_will_fail_when_car_is_not_in_parkingLot() {
            val parkingLot = ParkingLot(2)
            val ticket = parkingLot.park("川A33333")
            parkingLot.unpark(ticket)

            val exception = Assertions.assertThrows(UnparkFailedException::class.java) {
                parkingLot.unpark(ticket)
            }

            Assertions.assertEquals("Ticket is invalid", exception.message)
        }

        @Test
        internal fun unparking_with_ticket_twice_will_fail_when_car_is_in_parkingLot() {
            val parkingLot = ParkingLot(2)
            val ticket1 = parkingLot.park("川A33333")
            parkingLot.unpark(ticket1)
            val ticket2 = parkingLot.park("川A33333")

            val exception = Assertions.assertThrows(UnparkFailedException::class.java) {
                parkingLot.unpark(ticket1)
            }

            Assertions.assertEquals("Ticket is invalid", exception.message)
        }
    }
}
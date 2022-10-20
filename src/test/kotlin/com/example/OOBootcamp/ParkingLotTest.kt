package com.example.OOBootcamp

import com.example.OOBootcamp.exception.ParkingLotFullException
import com.example.OOBootcamp.exception.InvalidTicketException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ParkingLotTest {
    @Test
    internal fun can_park_successfully_when_parkingLot_has_capacity() {
        val parkingLot = ParkingLot(2)

        val ticket = parkingLot.park(Car("川A55555"))

        Assertions.assertNotNull(ticket)
    }

    @Test
    internal fun park_failed_when_parkingLot_is_full() {
        val parkingLot = ParkingLot(2)
        parkingLot.park(Car("川A33333"))
        parkingLot.park(Car("川A44444"))

        Assertions.assertThrows(ParkingLotFullException::class.java) {
            parkingLot.park(Car("川A55555"))
        }
    }

    @Test
    internal fun pickup_successfully() {
        val parkingLot = ParkingLot(2)
        val car = Car("川A33333")
        val ticket = parkingLot.park(car)

        val pickedCard = parkingLot.pickup(ticket)

        Assertions.assertEquals(car, pickedCard)
    }

    @Nested
    @DisplayName("pickup_failed")
    inner class PickupFailed {
        @Test
        internal fun pick_with_fake_ticket_will_fail() {
            val parkingLot = ParkingLot(2)
            parkingLot.park(Car("川A33333"))
            val fakeTicket = Ticket(Car("川A33334"))

            Assertions.assertThrows(InvalidTicketException::class.java) {
                parkingLot.pickup(fakeTicket)
            }
        }

        @Test
        internal fun pickup_with_ticket_twice_will_fail() {
            val parkingLot = ParkingLot(2)
            val ticket = parkingLot.park(Car("川A33333"))
            parkingLot.pickup(ticket)

            Assertions.assertThrows(InvalidTicketException::class.java) {
                parkingLot.pickup(ticket)
            }
        }
    }

    @Test
    internal fun will_park_fail_when_parkingLog_is_full_but_can_park_successfully_when_some_cards_has_been_picked_up() {
        val parkingLot = ParkingLot(2)
        parkingLot.park(Car("川A33333"))
        val ticket1 = parkingLot.park(Car("川A33334"))

        Assertions.assertThrows(ParkingLotFullException::class.java) {
            parkingLot.park(Car("川A33335"))
        }

        parkingLot.pickup(ticket1)
        val ticket2 = parkingLot.park(Car("川A33335"))

        Assertions.assertNotNull(ticket2)
    }
}
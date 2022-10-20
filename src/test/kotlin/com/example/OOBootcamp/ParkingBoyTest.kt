package com.example.OOBootcamp

import com.example.OOBootcamp.exception.InvalidTicketException
import com.example.OOBootcamp.exception.ParkingLotFullException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ParkingBoyTest {
    @Test
    internal fun should_park_successfully_when_the_parking_lots_is_not_full() {
        val parkingBoy = ParkingBoy()
        parkingBoy.addParkingLot(ParkingLot(2))

        val ticket = parkingBoy.park(Car("川A33333"))

        Assertions.assertNotNull(ticket)
    }

    @Test
    internal fun should_park_failed_when_the_parking_lots_is_full() {
        val parkingBoy = ParkingBoy()
        parkingBoy.addParkingLot(ParkingLot(1))
        parkingBoy.park(Car("川A33333"))

        Assertions.assertThrows(ParkingLotFullException::class.java) {
            parkingBoy.park(Car("川A33334"))
        }
    }

    @Test
    internal fun should_pick_up_successfuly_when_ticket_is_valid() {
        val parkingBoy = ParkingBoy()
        parkingBoy.addParkingLot(ParkingLot(1))
        val car = Car("川A33333")
        val ticket = parkingBoy.park(car)

        val pickedCar = parkingBoy.pickup(ticket)

        Assertions.assertEquals(car, pickedCar)
    }

    @Test
    internal fun should_pick_up_fail_when_ticket_is_invalid() {
        val parkingBoy = ParkingBoy()
        parkingBoy.addParkingLot(ParkingLot(1))
        parkingBoy.park(Car("川A33333"))

        Assertions.assertThrows(InvalidTicketException::class.java) {
            parkingBoy.pickup(Ticket())
        }
    }

    @Nested
    @DisplayName("parking_boy_owner_test")
    inner class ParkingBoyOwnerTest {
        @Test
        internal fun should_park_to_parking_lot_with_most_remaining_capacity() {
            val parkingBoy = ParkingBoy()
            val parkingLot1 = ParkingLot(2)
            parkingLot1.park(Car("川A33333"))
            parkingBoy.addParkingLot(parkingLot1)

            val parkingLot2 = ParkingLot(2)
            parkingBoy.addParkingLot(parkingLot2)

            parkingBoy.park(Car("川A44444"))

            Assertions.assertEquals(1, parkingLot2.remainingCapacity())
        }

        @Test
        internal fun should_park_to_parking_lot_inorder_when_parking_lots_have_same_remaining_parking_capacity() {
            val parkingBoy = ParkingBoy()
            val parkingLot1 = ParkingLot(2)
            parkingBoy.addParkingLot(parkingLot1)

            val parkingLot2 = ParkingLot(2)
            parkingBoy.addParkingLot(parkingLot2)

            parkingBoy.park(Car("川A44444"))

            Assertions.assertEquals(1, parkingLot1.remainingCapacity())
        }
    }
}
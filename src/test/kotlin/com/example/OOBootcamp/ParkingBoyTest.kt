package com.example.OOBootcamp

import com.example.OOBootcamp.exception.InvalidTicketException
import com.example.OOBootcamp.exception.ParkingLotFullException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ParkingBoyTest {
    @Nested
    @DisplayName("parking_inOrder_successfully")
    inner class ParkingInOrderSuccessfully {
        @Test
        internal fun should_park_successfully_given_single_parkingLot() {
            val parkingBoy = ParkingBoy()
            val parkingLot1 = ParkingLot(2)
            parkingBoy.addParkingLot(parkingLot1)

            val ticket=parkingBoy.park(Car("川A44444"))

            Assertions.assertNotNull(ticket)
        }

        @Test
        internal fun should_park_to_parkingLot_with_maxRemainingCapacity_given_multiple_notFullParkingLots_with_oneHasMaxRemainingCapacity() {
            val parkingBoy = ParkingBoy()
            val parkingLot1 = ParkingLot(1)
            parkingBoy.addParkingLot(parkingLot1)

            val parkingLot2 = ParkingLot(3)
            parkingBoy.addParkingLot(parkingLot2)

            val parkingLot3 = ParkingLot(2)
            parkingBoy.addParkingLot(parkingLot3)

            val ticket = parkingBoy.park(Car("川A44444"))

            Assertions.assertNotNull(ticket)
            Assertions.assertEquals(2, parkingLot2.remainingCapacity())
        }

        @Test
        internal fun should_park_to_parkingLot_inTheOrderItHasBeenAdded_given_multipleParkingLot_have_same_maxRemainingCapacity() {
            val parkingBoy = ParkingBoy()
            val parkingLot1 = ParkingLot(1)
            parkingBoy.addParkingLot(parkingLot1)

            val parkingLot2 = ParkingLot(2)
            parkingBoy.addParkingLot(parkingLot2)

            val parkingLot3 = ParkingLot(2)
            parkingBoy.addParkingLot(parkingLot3)

            val ticket = parkingBoy.park(Car("川A44444"))

            Assertions.assertNotNull(ticket)
            Assertions.assertEquals(1, parkingLot2.remainingCapacity())
        }
    }


    @Test
    internal fun should_return_failed_when_park_given_parkingBoy_is_full() {
        val parkingBoy = ParkingBoy()
        parkingBoy.addParkingLot(ParkingLot(1))
        parkingBoy.addParkingLot(ParkingLot(1))
        parkingBoy.park(Car("川A33333"))
        parkingBoy.park(Car("川A44444"))

        Assertions.assertThrows(ParkingLotFullException::class.java) {
            parkingBoy.park(Car("川A55555"))
        }
    }

    @Test
    internal fun should_return_car_when_pickUp_given_a_valid_ticket() {
        val parkingBoy = ParkingBoy()
        parkingBoy.addParkingLot(ParkingLot(1))
        parkingBoy.addParkingLot(ParkingLot(2))
        val car = Car("川A33333")
        val ticket = parkingBoy.park(car)

        val pickedCar = parkingBoy.pickup(ticket)

        Assertions.assertEquals(car, pickedCar)
    }

    @Test
    internal fun should_return_fail_when_pickUp_given_a_invalid_ticket() {
        val parkingBoy = ParkingBoy()
        parkingBoy.addParkingLot(ParkingLot(1))
        parkingBoy.addParkingLot(ParkingLot(1))

        Assertions.assertThrows(InvalidTicketException::class.java) {
            parkingBoy.pickup(Ticket())
        }
    }


}
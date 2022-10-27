package com.example.OOBootcamp

import com.example.OOBootcamp.exception.InvalidTicketException
import com.example.OOBootcamp.exception.ParkingLotFullException
import com.example.OOBootcamp.model.Car
import com.example.OOBootcamp.model.Ticket
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SmartParkingBoyTest {
    @Nested
    @DisplayName("parking_inOrder_successfully")
    inner class ParkingInOrderSuccessfully {
        @Test
        internal fun should_park_successfully_given_single_parkingLot() {
            val parkingLot = ParkingLot(2)
            val parkingBoy = SmartParkingBoy(listOf(parkingLot))
            val car = Car("川A44444")

            val ticket = parkingBoy.park(car)

            Assertions.assertEquals(car, parkingLot.pickup(ticket))
        }

        @Test
        internal fun should_park_to_parkingLot_with_maxRemainingCapacity_given_multiple_notFullParkingLots_with_oneHasMaxRemainingCapacity() {
            val parkingLot1 = ParkingLot(1)
            val parkingLot2 = ParkingLot(3)
            val parkingLot3 = ParkingLot(2)
            val parkingBoy = SmartParkingBoy(listOf(parkingLot1, parkingLot2, parkingLot3))
            val car = Car("川A44444")

            val ticket = parkingBoy.park(car)

            Assertions.assertEquals(car, parkingLot2.pickup(ticket))
        }

        @Test
        internal fun should_park_to_parkingLot_inTheOrderItHasBeenAdded_given_multipleParkingLot_have_same_maxRemainingCapacity() {
            val parkingLot1 = ParkingLot(1)
            val parkingLot2 = ParkingLot(2)
            val parkingLot3 = ParkingLot(2)
            val parkingBoy = SmartParkingBoy(listOf(parkingLot1, parkingLot2, parkingLot3))
            val car = Car("川A44444")

            val ticket = parkingBoy.park(car)

            Assertions.assertEquals(car, parkingLot2.pickup(ticket))
        }
    }


    @Test
    internal fun should_return_ParkingLotFullException_when_park_given_parkingBoy_is_full() {
        val parkingLot1 = ParkingLot(1)
        val parkingLot2 = ParkingLot(1)
        parkingLot1.park(Car("川A33333"))
        parkingLot2.park(Car("川A44444"))
        val parkingBoy = SmartParkingBoy(listOf(parkingLot1, parkingLot2))

        Assertions.assertThrows(ParkingLotFullException::class.java) {
            parkingBoy.park(Car("川A55555"))
        }
    }

    @Test
    internal fun should_return_car_when_pickUp_given_a_valid_ticket() {
        val parkingBoy = SmartParkingBoy(listOf(ParkingLot(1), ParkingLot(2)))
        val car = Car("川A33333")
        val ticket = parkingBoy.park(car)

        val pickedCar = parkingBoy.pickup(ticket)

        Assertions.assertEquals(car, pickedCar)
    }

    @Test
    internal fun should_return_InvalidTicketException_when_pickUp_given_a_invalid_ticket() {
        val parkingBoy = SmartParkingBoy(listOf(ParkingLot(1), ParkingLot(1)))

        Assertions.assertThrows(InvalidTicketException::class.java) {
            parkingBoy.pickup(Ticket())
        }
    }
}
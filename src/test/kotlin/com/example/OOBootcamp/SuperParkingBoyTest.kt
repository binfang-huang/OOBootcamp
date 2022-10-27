package com.example.OOBootcamp

import com.example.OOBootcamp.exception.InvalidTicketException
import com.example.OOBootcamp.exception.ParkingLotFullException
import com.example.OOBootcamp.model.Car
import com.example.OOBootcamp.model.Ticket
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SuperParkingBoyTest {
    @Test
    internal fun should_park_in_order_and_get_ticket_when_park_given_parkingLots_is_not_full() {
        val parkingLot1 = ParkingLot(3)
        parkingLot1.park(Car("川A11111"))
        parkingLot1.park(Car("川A22222"))

        val parkingLot2 = ParkingLot(2)
        parkingLot2.park(Car("川A33333"))

        val parkingLot3 = ParkingLot(4)
        parkingLot3.park(Car("川A44444"))
        parkingLot3.park(Car("川A55555"))

        val parkingBoy =
            SuperParkingBoy(listOf(parkingLot1, parkingLot2, parkingLot3))

        val car = Car("川A44444")

        val ticket = parkingBoy.park(car)

        Assertions.assertEquals(car, parkingLot2.pickup(ticket))
    }

    @Test
    internal fun should_throw_ParkingLotFullException_when_park_given_parkingLots_is_full() {
        val parkingLot1 = ParkingLot(1)
        parkingLot1.park(Car("川A11111"))

        val parkingLot2 = ParkingLot(1)
        parkingLot2.park(Car("川A33333"))

        val parkingBoy = SuperParkingBoy(listOf(parkingLot1, parkingLot2))

        Assertions.assertThrows(ParkingLotFullException::class.java) {
            parkingBoy.park(Car("川A44444"))
        }
    }

    @Test
    internal fun should_get_car_when_pickup_given_valid_ticket() {
        val parkingBoy = SuperParkingBoy(listOf(ParkingLot(1), ParkingLot(1)))
        val car = Car("川A44444")
        val ticket = parkingBoy.park(car)

        val pickedCar = parkingBoy.pickup(ticket)

        Assertions.assertEquals(car, pickedCar)
    }

    @Test
    internal fun should_get_InvalidTicketException_when_pickup_given_invalid_ticket() {
        val parkingBoy = SuperParkingBoy(listOf(ParkingLot(1), ParkingLot(1)))
        val car = Car("川A44444")
        parkingBoy.park(car)

        Assertions.assertThrows(InvalidTicketException::class.java) {
            parkingBoy.pickup(Ticket())
        }
    }
}
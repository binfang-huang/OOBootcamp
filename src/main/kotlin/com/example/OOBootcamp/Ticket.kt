package com.example.OOBootcamp

import java.util.*

data class Ticket( val car: Car,private val id: String = UUID.randomUUID().toString())
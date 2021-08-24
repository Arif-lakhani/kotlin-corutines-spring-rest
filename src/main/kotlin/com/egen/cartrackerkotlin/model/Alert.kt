package com.egen.cartrackerkotlin.model

import java.sql.Timestamp

data class Alert(
    val id: String,
    val timestamp: Timestamp,
    val priority: String,
    val vin: String,
    val message: String
)

package com.egen.cartrackerkotlin.model

import org.springframework.data.annotation.Id
import java.sql.Timestamp
import java.util.*

data class Readings(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val vin: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Timestamp,
    val fuelVolume: Double,
    val speed: Double,
    val engineHp: Double,
    val checkEngineLightOn: Boolean,
    val engineCoolantLow: Boolean,
    val cruiseControlOn: Boolean,
    val engineRpm: Double,
    val tires: Tires

    )

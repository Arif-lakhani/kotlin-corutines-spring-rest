package com.egen.cartrackerkotlin.model

import org.springframework.data.annotation.Id
import java.sql.Timestamp

data class Vehicle(
  @Id
  val vin: String,
  val make: String,
  val model: String,
  val year: Int = 0,
  val redLineRpm: Double,
  val maxFuelVolume: Double,
  val lastServiceDate: Timestamp
)

package com.egen.cartrackerkotlin.service

import com.egen.cartrackerkotlin.model.Vehicle
import kotlinx.coroutines.flow.Flow

interface IVehicleService {
    suspend fun findAll(): Flow<Vehicle>
    suspend fun insert(vehicles: List<Vehicle>): Flow<Vehicle>
    suspend fun findById(vin: String): Vehicle
}

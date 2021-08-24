package com.egen.cartrackerkotlin.service.impl

import com.egen.cartrackerkotlin.model.Vehicle
import com.egen.cartrackerkotlin.repo.IVehicleRepo
import com.egen.cartrackerkotlin.service.IVehicleService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class VehicleService(private val vehicleRepo: IVehicleRepo) : IVehicleService {
    override suspend fun findAll(): Flow<Vehicle> = vehicleRepo.findAll().asFlow()

    override suspend fun insert(vehicles: List<Vehicle>): Flow<Vehicle> = vehicleRepo.saveAll(vehicles).asFlow()

    override suspend fun findById(vin: String): Vehicle = vehicleRepo.findById(vin).awaitFirst()
}

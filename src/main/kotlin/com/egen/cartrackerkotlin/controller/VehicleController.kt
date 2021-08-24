package com.egen.cartrackerkotlin.controller

import com.egen.cartrackerkotlin.model.Vehicle
import com.egen.cartrackerkotlin.service.IVehicleService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class VehicleController( val service: IVehicleService) {

    @GetMapping(value = ["/vehicles/{id}"])
    suspend fun findById(@PathVariable("id") vin: String): Vehicle = coroutineScope {
        service.findById(vin)
    }

    @PutMapping(value = ["/vehicles"])
    suspend fun insert(@RequestBody vehicle: List<Vehicle>): Flow<Vehicle> = coroutineScope {
        service.insert(vehicle)
    }

    @GetMapping(value = ["/vehicles"])
    suspend fun findAll(): Flow<Vehicle> = coroutineScope  {
        service.findAll()
    }
}

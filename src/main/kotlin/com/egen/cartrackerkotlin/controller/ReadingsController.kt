package com.egen.cartrackerkotlin.controller

import com.egen.cartrackerkotlin.model.Readings
import com.egen.cartrackerkotlin.service.IReadingsService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
class ReadingsController(val service: IReadingsService) {

    @PostMapping
    suspend fun addReadings(@RequestBody readings: Readings): Readings = coroutineScope {
         service.addReadings(readings)
    }

    @GetMapping(value = ["/{id}/{range}"])
    suspend fun getReadingsWithinRange(
        @PathVariable("id") vinId: String,
        @PathVariable("range") range: Float
    ): Flow<Readings>  = coroutineScope{
         service.getReadingWithinRange(vinId, range)
    }

    @GetMapping(value = ["/{id}"])
    suspend fun findById(@PathVariable("id") vin: String): Flow<Readings> = coroutineScope{
         service.findByVin(vin)
    }

}

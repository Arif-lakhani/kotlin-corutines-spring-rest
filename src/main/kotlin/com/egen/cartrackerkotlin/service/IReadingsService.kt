package com.egen.cartrackerkotlin.service

import com.egen.cartrackerkotlin.model.Readings
import kotlinx.coroutines.flow.Flow
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface IReadingsService {
    suspend fun findByVin(vin: String): Flow<Readings>
    suspend fun addReadings(readings: Readings): Readings
    suspend fun getReadingWithinRange(vinId: String, range: Float): Flow<Readings>
}

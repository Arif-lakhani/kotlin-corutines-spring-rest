package com.egen.cartrackerkotlin.service.impl

import com.egen.cartrackerkotlin.model.Alert
import com.egen.cartrackerkotlin.model.Readings
import com.egen.cartrackerkotlin.model.Vehicle
import com.egen.cartrackerkotlin.repo.IAlertsRepo
import com.egen.cartrackerkotlin.repo.IReadingsRepo
import com.egen.cartrackerkotlin.repo.IVehicleRepo
import com.egen.cartrackerkotlin.service.IReadingsService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.util.*

@Service
class ReadingsService(private val readingsRepo: IReadingsRepo,
                      private val vehicleRepo: IVehicleRepo,
                      private val alertRepo: IAlertsRepo) : IReadingsService {
    override suspend fun findByVin(vin: String): Flow<Readings> {
        return readingsRepo.findAllByVin(vin).asFlow()
    }

    override suspend fun addReadings(readings: Readings): Readings = coroutineScope  {
        val thisVehicle: Vehicle =  vehicleRepo.findById(readings.vin).awaitFirst()
                if (readings.engineRpm > thisVehicle.redLineRpm) {
                    val rpmAlert= Alert(
                        message = "RPM crossed red line," ,
                        priority = "High",
                        timestamp = readings.timestamp,
                        id = UUID.randomUUID().toString(),
                        vin = readings.vin)
                    launch {  alertRepo.save(rpmAlert) }
                }
                if (readings.fuelVolume < 10 * thisVehicle.maxFuelVolume / 100) {
                    val fuelAlert = Alert(
                        message = "Fuel vol low," ,
                        priority = "medium",
                        timestamp = readings.timestamp,
                        id = UUID.randomUUID().toString(),
                        vin = readings.vin)
                    launch {  alertRepo.save(fuelAlert) }

                }
                if (readings.tires.frontLeft < 32 || readings.tires
                        .frontLeft > 36 || readings.tires.frontRight < 32 || readings.tires
                        .frontRight > 36 || readings.tires.rearLeft < 32 || readings.tires
                        .frontLeft > 36 || readings.tires.rearRight < 32 || readings.tires
                        .rearRight > 36
                ) {
                    val tireAlert = Alert(
                        message = "Tire pressure check needed," ,
                        priority = "low",
                        timestamp = readings.timestamp,
                        id = UUID.randomUUID().toString(),
                        vin = readings.vin
                    )
                    launch { alertRepo.save(tireAlert) }
                }
                if (readings.checkEngineLightOn || readings.engineCoolantLow) {
                    val engineAlert= Alert(
                        message = "Engine check required" ,
                        priority = "low",
                        timestamp = readings.timestamp,
                        id = UUID.randomUUID().toString(),
                        vin = readings.vin)
                    alertRepo.save(engineAlert)
                }
            readingsRepo.save(readings).awaitFirst()
    }

    override suspend fun getReadingWithinRange(vinId: String, range: Float): Flow<Readings> {
        val timestamp = Timestamp((System.currentTimeMillis() - range * 60 * 1000).toLong())
        return readingsRepo.findReadingsWithinRange(vin = vinId,timestamp = timestamp ).asFlow()
    }

}

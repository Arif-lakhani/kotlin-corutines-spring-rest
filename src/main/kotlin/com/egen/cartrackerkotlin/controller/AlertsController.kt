package com.egen.cartrackerkotlin.controller

import com.egen.cartrackerkotlin.model.Alert
import com.egen.cartrackerkotlin.model.HighAlertsDto
import com.egen.cartrackerkotlin.service.IAlertService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class AlertsController( private val service: IAlertService) {

    @GetMapping("/highAlertsCount")
    suspend fun getAllHighAlerts():HighAlertsDto = coroutineScope {
         service.findAllHighAlerts()
    }

    @GetMapping(value = ["{vin}"])
    suspend fun findByVin(@PathVariable vin: String): Flow<Alert> = coroutineScope {
         service.findById(vin)
    }
}

package com.egen.cartrackerkotlin.service.impl

import com.egen.cartrackerkotlin.model.Alert
import com.egen.cartrackerkotlin.model.HighAlertsDto
import com.egen.cartrackerkotlin.repo.IAlertsRepo
import com.egen.cartrackerkotlin.service.IAlertService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Service

@Service
class AlertServiceImpl(private val alertsRepo: IAlertsRepo): IAlertService {

    override suspend fun findAllHighAlerts(): HighAlertsDto {
            return alertsRepo.findAllHighAlerts().awaitFirst()
    }

    override suspend fun findById(vin: String): Flow<Alert> {
        return alertsRepo.findAllByVin(vin).asFlow()
    }
}

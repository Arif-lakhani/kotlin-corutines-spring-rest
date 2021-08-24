package com.egen.cartrackerkotlin.service

import com.egen.cartrackerkotlin.model.Alert
import com.egen.cartrackerkotlin.model.HighAlertsDto
import kotlinx.coroutines.flow.Flow

interface IAlertService {
    suspend fun findAllHighAlerts(): HighAlertsDto
    suspend fun findById(vin: String): Flow<Alert>
}

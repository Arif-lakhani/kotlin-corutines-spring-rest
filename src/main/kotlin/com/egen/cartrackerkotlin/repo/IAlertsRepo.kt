package com.egen.cartrackerkotlin.repo

import com.egen.cartrackerkotlin.model.Alert
import com.egen.cartrackerkotlin.model.HighAlertsDto
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface IAlertsRepo: ReactiveCrudRepository<Alert,String> {
    @Query(value = "SELECT a.vin, count(a.vin) as countOfAlerts FROM Alert a  WHERE a.timestamp > ( now() - INTERVAL '1 DAY' ) and a.priority='High' GROUP BY a.vin ORDER BY countOfAlerts desc")
    fun findAllHighAlerts(): Flux<HighAlertsDto>

    fun findAllByVin(vin: String): Flux<Alert>
}

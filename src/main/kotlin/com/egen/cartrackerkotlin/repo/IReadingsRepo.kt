package com.egen.cartrackerkotlin.repo

import com.egen.cartrackerkotlin.model.Readings
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.sql.Timestamp

@Repository
interface IReadingsRepo: ReactiveCrudRepository<Readings,String>{
    @Query("SELECT rdn FROM Readings rdn WHERE rdn.timestamp > :timestamp AND rdn.vin = :vin")
    fun findReadingsWithinRange(@Param("vin") vin: String, @Param("timestamp") timestamp: Timestamp): Flux<Readings>

    fun findAllByVin(vin: String): Flux<Readings>
}

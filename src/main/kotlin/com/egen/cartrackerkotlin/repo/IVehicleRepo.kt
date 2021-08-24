package com.egen.cartrackerkotlin.repo

import com.egen.cartrackerkotlin.model.Vehicle
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository


@Repository
interface IVehicleRepo: ReactiveCrudRepository<Vehicle,String>

package com.egen.cartrackerkotlin.model

import org.springframework.data.annotation.Id
import java.util.*

class Tires(
    @Id
     var id: String =  UUID.randomUUID().toString(),
     val frontLeft: Double,
     val frontRight: Double,
     val rearLeft: Double,
     val rearRight: Double
)

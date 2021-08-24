package com.egen.cartrackerkotlin

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class CartrackerKotlinApplication

fun main(args: Array<String>) {
	runApplication<CartrackerKotlinApplication>(*args)
}

@Configuration
class DbConfig {
	@Bean
	fun connectionFactory(): PostgresqlConnectionFactory {
		return PostgresqlConnectionFactory(
			PostgresqlConnectionConfiguration.builder()
				.host("localhost")
				.database("postgres")
				.username("ariflakhani")
				.password("")
				.build()
		)
	}
}



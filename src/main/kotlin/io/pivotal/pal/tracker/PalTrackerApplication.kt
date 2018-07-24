package io.pivotal.pal.tracker

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.pivotal.pal.tracker.repositories.JdbcTimeEntryRepository
import org.springframework.boot.SpringApplication
import org.springframework.boot.actuate.metrics.rich.InMemoryRichGaugeRepository
import org.springframework.boot.actuate.metrics.writer.DefaultCounterService
import org.springframework.boot.actuate.metrics.writer.DefaultGaugeService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import javax.sql.DataSource


@SpringBootApplication
class PalTrackerApplication {
    @Bean
    fun timeEntryRepository(dataSource: DataSource) = JdbcTimeEntryRepository(dataSource)

    @Bean
    fun counter() = DefaultCounterService(InMemoryRichGaugeRepository())

    @Bean
    fun gauge() = DefaultGaugeService(InMemoryRichGaugeRepository())

    @Bean
    fun jsonObjectMapper(): ObjectMapper {
        return Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
                .modules(listOf(JavaTimeModule(), KotlinModule()))
                .build()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(PalTrackerApplication::class.java, *args)
}

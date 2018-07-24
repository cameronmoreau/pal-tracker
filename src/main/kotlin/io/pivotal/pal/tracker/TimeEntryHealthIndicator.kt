package io.pivotal.pal.tracker

import io.pivotal.pal.tracker.repositories.TimeEntryRepository
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

@Component
class TimeEntryHealthIndicator(val timeEntryRepository: TimeEntryRepository): HealthIndicator {
    override fun health(): Health {
        val count = timeEntryRepository.list().size
        return if (count < 5) {
            Health.Builder().up().build()
        }
        else {
            Health.Builder().down().build()
        }
    }
}
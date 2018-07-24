package io.pivotal.pal.tracker.repositories

import io.pivotal.pal.tracker.domain.TimeEntry

interface TimeEntryRepository {
    fun create(timeEntry: TimeEntry): TimeEntry?

    fun find(id: Long): TimeEntry?

    fun list(): List<TimeEntry>

    fun update(id: Long, timeEntry: TimeEntry): TimeEntry?

    fun delete(id: Long)
}

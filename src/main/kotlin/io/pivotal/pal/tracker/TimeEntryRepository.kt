package io.pivotal.pal.tracker

interface TimeEntryRepository {
    fun create(timeEntry: TimeEntry): TimeEntry?

    fun find(id: Long): TimeEntry?

    fun list(): List<TimeEntry>

    fun update(id: Long, timeEntry: TimeEntry): TimeEntry?

    fun delete(id: Long)
}

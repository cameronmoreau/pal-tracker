package io.pivotal.pal.tracker.repositories

import io.pivotal.pal.tracker.domain.TimeEntry

class InMemoryTimeEntryRepository : TimeEntryRepository {
    private var timeEntries: MutableMap<Long, TimeEntry> = mutableMapOf()
    private var nextId: Long = 1L

    override fun create(timeEntry: TimeEntry): TimeEntry {
        val createdTimeEntry = timeEntry.copy(id=nextId++)
        timeEntries[createdTimeEntry.id!!] = createdTimeEntry
        return createdTimeEntry
    }

    override fun find(id: Long): TimeEntry? {
        return timeEntries[id]
    }

    override fun list(): List<TimeEntry> {
        return timeEntries.values.toList()
    }

    override fun update(id: Long, timeEntry: TimeEntry): TimeEntry {
        val updatedTimeEntry = timeEntry.copy(id=id)
        timeEntries[id] = updatedTimeEntry
        return updatedTimeEntry
    }

    override fun delete(id: Long) {
        timeEntries.remove(id)
    }
}
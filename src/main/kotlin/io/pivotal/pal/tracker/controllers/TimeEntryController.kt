package io.pivotal.pal.tracker.controllers

import io.pivotal.pal.tracker.domain.TimeEntry
import io.pivotal.pal.tracker.repositories.TimeEntryRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.boot.actuate.metrics.CounterService
import org.springframework.boot.actuate.metrics.GaugeService

@RestController
@RequestMapping("/time-entries")
class TimeEntryController(
        val timeEntryRepository: TimeEntryRepository,
        val counter: CounterService,
        val gauge: GaugeService
        ) {

    @PostMapping
    fun create(@RequestBody timeEntry: TimeEntry): ResponseEntity<TimeEntry?> {
        val returnedTimeEntry = timeEntryRepository.create(timeEntry)
        counter.increment("TimeEntry.created")
        gauge.submit("timeEntries.count", timeEntryRepository.list().size.toDouble())
        return ResponseEntity(returnedTimeEntry, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    // TODO: Use when for refactor
    fun read(@PathVariable id: Long): ResponseEntity<TimeEntry> {
        val returnedTimeEntry = timeEntryRepository.find(id)
        return if (returnedTimeEntry != null) {
            counter.increment("TimeEntry.read")
            ResponseEntity(returnedTimeEntry, HttpStatus.OK)
        }
        else {ResponseEntity(HttpStatus.NOT_FOUND)}
    }

    @GetMapping
    fun list(): ResponseEntity<List<TimeEntry>> {
        val returnedTimeEntryList = timeEntryRepository.list()
        counter.increment("TimeEntry.listed")
        return ResponseEntity(returnedTimeEntryList, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody timeEntry: TimeEntry): ResponseEntity<TimeEntry> {
        val returnedTimeEntry = timeEntryRepository.update(id, timeEntry)
        return if (returnedTimeEntry != null) {
            counter.increment("TimeEntry.updated")
            ResponseEntity(returnedTimeEntry, HttpStatus.OK)
        }
        else {ResponseEntity(HttpStatus.NOT_FOUND)}
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Void> {
        timeEntryRepository.delete(id)
        counter.increment("TimeEntry.deleted")
        gauge.submit("timeEntries.count", timeEntryRepository.list().size.toDouble())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
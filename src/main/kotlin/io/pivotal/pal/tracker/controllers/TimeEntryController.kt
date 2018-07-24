package io.pivotal.pal.tracker.controllers

import io.pivotal.pal.tracker.domain.TimeEntry
import io.pivotal.pal.tracker.repositories.TimeEntryRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/time-entries")
class TimeEntryController(val timeEntryRepository: TimeEntryRepository) {

    @PostMapping
    fun create(@RequestBody timeEntry: TimeEntry): ResponseEntity<TimeEntry?> {
        val returnedTimeEntry = timeEntryRepository.create(timeEntry)
        return ResponseEntity(returnedTimeEntry, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun read(@PathVariable id: Long): ResponseEntity<TimeEntry> {
        val returnedTimeEntry = timeEntryRepository.find(id)
        return if (returnedTimeEntry != null) {ResponseEntity(returnedTimeEntry, HttpStatus.OK)}
        else {ResponseEntity(HttpStatus.NOT_FOUND)}
    }

    @GetMapping
    fun list(): ResponseEntity<List<TimeEntry>> {
        val returnedTimeEntryList = timeEntryRepository.list()
        return ResponseEntity(returnedTimeEntryList, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody timeEntry: TimeEntry): ResponseEntity<TimeEntry> {
        val returnedTimeEntry = timeEntryRepository.update(id, timeEntry)
        return if (returnedTimeEntry != null) {ResponseEntity(returnedTimeEntry, HttpStatus.OK)}
        else {ResponseEntity(HttpStatus.NOT_FOUND)}
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Void> {
        timeEntryRepository.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
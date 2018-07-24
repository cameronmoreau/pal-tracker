package io.pivotal.pal.tracker

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TimeEntryController(timeEntryRepository: TimeEntryRepository) {

    val timeEntryRepository = timeEntryRepository

    @PostMapping("/time-entries")
    fun create(@RequestBody timeEntry: TimeEntry): ResponseEntity<TimeEntry?> {
        val returnedTimeEntry = timeEntryRepository.create(timeEntry)
        return ResponseEntity(returnedTimeEntry, HttpStatus.CREATED)
    }

    @GetMapping("/time-entries/{id}")
    fun read(@PathVariable id: Long): ResponseEntity<TimeEntry> {
        val returnedTimeEntry = timeEntryRepository.find(id)
        return if (returnedTimeEntry != null) {ResponseEntity(returnedTimeEntry, HttpStatus.OK)}
        else {ResponseEntity(HttpStatus.NOT_FOUND)}
    }

    @GetMapping("/time-entries")
    fun list(): ResponseEntity<List<TimeEntry>> {
        val returnedTimeEntryList = timeEntryRepository.list()
        return ResponseEntity(returnedTimeEntryList, HttpStatus.OK)
    }

    @PutMapping("/time-entries/{id}")
    fun update(@PathVariable id: Long, @RequestBody timeEntry: TimeEntry): ResponseEntity<TimeEntry> {
        val returnedTimeEntry = timeEntryRepository.update(id, timeEntry)
        return if (returnedTimeEntry != null) {ResponseEntity(returnedTimeEntry, HttpStatus.OK)}
        else {ResponseEntity(HttpStatus.NOT_FOUND)}
    }

    @DeleteMapping("/time-entries/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Void> {
        timeEntryRepository.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
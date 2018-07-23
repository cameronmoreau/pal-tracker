package io.pivotal.pal.tracker

import java.time.LocalDate

data class TimeEntry(
        val id: Long?,
        val projectId: Long,
        val userId: Long,
        val date: LocalDate,
        val hours: Int
) {
    constructor(projectId: Long, userId: Long, date: LocalDate, hours: Int) :
            this(null, projectId, userId, date, hours)
}
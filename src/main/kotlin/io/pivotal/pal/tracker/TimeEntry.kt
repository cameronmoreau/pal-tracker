package io.pivotal.pal.tracker

import java.time.LocalDate

data class TimeEntry(
        var id: Long? = null,
        var projectId: Long? = null,
        var userId: Long? = null,
        var date: LocalDate? = null,
        var hours: Int? = null
) {

    constructor(projectId: Long, userId: Long, date: LocalDate, hours: Int) :
            this(null, projectId, userId, date, hours)

//    fun getId() : Long? = id
}
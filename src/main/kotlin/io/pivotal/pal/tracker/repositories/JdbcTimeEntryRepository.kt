package io.pivotal.pal.tracker.repositories

import io.pivotal.pal.tracker.domain.TimeEntry
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import java.sql.Date
import java.sql.ResultSet
import java.sql.Statement
import javax.sql.DataSource

class JdbcTimeEntryRepository(dataSource: DataSource): TimeEntryRepository {
    private val jdbcTemplate = JdbcTemplate(dataSource)
    private val timeEntryRowMapper = RowMapper<TimeEntry>({ rs, _ ->
        TimeEntry(
                rs.getLong("id"),
                rs.getLong("project_id"),
                rs.getLong("user_id"),
                rs.getDate("date").toLocalDate(),
                rs.getInt("hours")
        )
    })

    override fun create(timeEntry: TimeEntry): TimeEntry? {
        val sql = "INSERT INTO time_entries (project_id, user_id, date, hours) VALUES (?, ?, ?, ?)"
        val generatedKeyHolder = GeneratedKeyHolder()

        jdbcTemplate.update({
            val query = it.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            query.setLong(1, timeEntry.projectId)
            query.setLong(2, timeEntry.userId)
            query.setDate(3, Date.valueOf(timeEntry.date))
            query.setInt(4, timeEntry.hours)

            query
        }, generatedKeyHolder)

        return find(generatedKeyHolder.key.toLong())
    }

    override fun find(id: Long): TimeEntry? {
        val sql = "SELECT id, project_id, user_id, date, hours FROM time_entries WHERE id = ?"
        var fetchedTimeEntry: TimeEntry? = null

        jdbcTemplate.query({
            val query = it.prepareStatement(sql)
            query.setLong(1, id)

            query
        }, {
            fetchedTimeEntry = timeEntryRowMapper.mapRow(it, 0)
        })

        return fetchedTimeEntry
    }

    override fun list(): List<TimeEntry> {
        val sql = "SELECT id, project_id, user_id, date, hours FROM time_entries"
        val fetchedTimeEntries: MutableList<TimeEntry> = mutableListOf()

        jdbcTemplate.query(sql, {
            fetchedTimeEntries.add(timeEntryRowMapper.mapRow(it, 0))
        })

        return fetchedTimeEntries
    }

    override fun update(id: Long, timeEntry: TimeEntry): TimeEntry? {
        val sql = "UPDATE time_entries SET project_id = ?, user_id = ?, date = ?, hours = ? WHERE id = ?"

        jdbcTemplate.update({
            val query = it.prepareStatement(sql)
            query.setLong(1, timeEntry.projectId)
            query.setLong(2, timeEntry.userId)
            query.setDate(3, Date.valueOf(timeEntry.date))
            query.setInt(4, timeEntry.hours)
            query.setLong(5, id)

            query
        })

        return find(id)
    }

    override fun delete(id: Long) {
        val sql = "DELETE FROM time_entries WHERE id = ?"

        jdbcTemplate.update({
            val query = it.prepareStatement(sql)
            query.setLong(1, id)

            query
        })
    }
}
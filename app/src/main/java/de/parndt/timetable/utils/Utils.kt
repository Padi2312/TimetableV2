package de.parndt.timetable.utils

import okhttp3.Request
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {

    fun dateFormater(pattern: String): DateTimeFormatter? {
        return DateTimeFormatter.ofPattern(pattern)
    }

    fun reqeust(url: String): Request {
        return Request.Builder().url(url).build()
    }

    fun dateNow(): LocalDate {
        return LocalDate.now()
    }

    fun dateTimeNow(): LocalDateTime {
        return LocalDateTime.now()
    }

}
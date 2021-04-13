package de.parndt.Timetable.utils

import java.time.format.DateTimeFormatter

object Utils {

    fun dateFormater(pattern: String): DateTimeFormatter? {
        return DateTimeFormatter.ofPattern(pattern)
    }
}
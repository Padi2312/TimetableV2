package de.parndt.Timetable.timetable

import java.util.*

class Lecture(
    val id: String,
    val name: String,
    val date: String,
    val time: String
) {
    constructor(name: String, date: String, time: String) : this(
        UUID.randomUUID().toString(),
        name,
        date,
        time
    )
}

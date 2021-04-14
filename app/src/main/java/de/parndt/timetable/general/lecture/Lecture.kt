package de.parndt.timetable.general.lecture

import java.util.*

class Lecture(
    val id: String,
    val name: String,
    val date: String,
    val time: String,
    val course: String,
    val room: String
) {

    constructor(name: String, date: String, time: String, course: String, room: String) : this(
        UUID.randomUUID().toString(),
        name, date, time, course, room
    )
}
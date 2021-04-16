/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.general.lecture

import com.opencsv.bean.CsvBindByName

class LectureCSVModel {
    @CsvBindByName(column = "Datum")
    var date: String? = null

    @CsvBindByName(column = "Zeiten")
    var times: String? = null

    @CsvBindByName(column = "Name")
    var name: String? = null

    @CsvBindByName(column = "Kurs")
    var course: String? = null

    @CsvBindByName(column = "Raum")
    var room: String? = null

    constructor()
    constructor(
        date: String, times: String, name: String, course: String, room: String
    ) {
        this.date = date
        this.times = times
        this.name = name
        this.course = course
        this.room = room

    }
}
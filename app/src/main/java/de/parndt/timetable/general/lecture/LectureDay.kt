/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.general.lecture

import de.parndt.timetable.database.models.LectureEntity
import de.parndt.timetable.utils.Utils
import java.time.LocalDate
import java.util.*


class LectureDay(val id: String, private val date: String, val lecturesList: List<LectureEntity>) {

    var Date = date
        get() {
            return parseDateToString(getDateValue())
        }

    constructor(date: String, lecturesList: List<LectureEntity>) : this(
        UUID.randomUUID().toString(),
        date,
        lecturesList
    )

    private fun parseDateToString(date: LocalDate): String {
        return date.format(Utils.dateFormater("EE dd.MM.yy"))
    }

    open fun getDateValue(): LocalDate {
        return LocalDate.parse(date, Utils.dateFormater("yyyy-MM-dd HH:mm:ss"))
    }

}


/*class Weekend(
    id: String,
    dateSaturday: String,
    dateSunday: String,
    lecturesList: List<Lecture> = listOf()
) : LectureDay(id, dateSaturday, lecturesList) {

    private var saturdayDate = dateSaturday
    private var sundayDate = dateSunday

    override fun getDateValue(): LocalDate {
        val localeDateSaturday = LocalDate.parse(saturdayDate, Utils.dateFormater("dd.MM.yy"))
        val localeDateSunday = LocalDate.parse(sundayDate, Utils.dateFormater("dd.MM.yy"))

        return if (LocalDate.now().equals(localeDateSaturday))
            localeDateSaturday
        else
            localeDateSunday

    }
}
*/
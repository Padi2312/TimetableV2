package de.parndt.Timetable.timetable

import de.parndt.Timetable.utils.Utils
import java.time.LocalDate
import java.util.*


sealed class LectureDay(val id: String, val date: String, val lecturesList: List<Lecture>) {

    constructor(date: String, lecturesList: List<Lecture>) : this(
        UUID.randomUUID().toString(),
        date,
        lecturesList
    )

    open fun getDateValue(): LocalDate {
        return LocalDate.parse(date, Utils.dateFormater("EE dd.MM.yy"))
    }
}

class PastLectureDay(id: String, dateOfDay: String, lecturesList: List<Lecture>) :
    LectureDay(id, dateOfDay, lecturesList) {
    constructor(date: String, lecturesList: List<Lecture>) : this(
        UUID.randomUUID().toString(),
        date,
        lecturesList
    )
}

class CurrentLectureDay(id: String, dateOfDay: String, lecturesList: List<Lecture>) :
    LectureDay(id, dateOfDay, lecturesList) {
    constructor(date: String, lecturesList: List<Lecture>) : this(
        UUID.randomUUID().toString(),
        date,
        lecturesList
    )
}


class DefaultLectureDay(id: String, dateOfDay: String, lecturesList: List<Lecture>) :
    LectureDay(id, dateOfDay, lecturesList) {
    constructor(date: String, lecturesList: List<Lecture>) : this(
        UUID.randomUUID().toString(),
        date,
        lecturesList
    )
}



class Weekend(
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

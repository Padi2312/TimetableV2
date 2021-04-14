package de.parndt.timetable.timetable

import de.parndt.timetable.general.Config
import de.parndt.timetable.general.TimetableParser
import de.parndt.timetable.general.lecture.Lecture
import de.parndt.timetable.general.lecture.LectureDay
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimetableUseCase @Inject constructor(
    private val timetableParser: TimetableParser,
    private val config: Config
) {

    fun getLectures(): List<LectureDay> {
        val listLectureDay: MutableList<LectureDay> = mutableListOf()
        val lectures = timetableParser.getLectures() ?: listOf()

        val courseSpecificLectures = lecturesFromCourse(lectures, config.getCourse())

        val lecturesGroupedByDate = courseSpecificLectures.groupBy { it.date }.toSortedMap()

        lecturesGroupedByDate.forEach { (date, lectureDay) ->
            listLectureDay.add(LectureDay(date, lectureDay))
        }
        return listLectureDay
    }

    fun lecturesFromCourse(lectures: List<Lecture>, course: String): List<Lecture> {
        return lectures.filter { lecture ->
            lecture.course.contains(course)
        }
    }

    fun getCurrentDate(): LocalDate {
        return LocalDate.now(ZoneId.of("Europe/Paris"))
    }

}
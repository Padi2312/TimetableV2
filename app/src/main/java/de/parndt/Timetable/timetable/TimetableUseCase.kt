package de.parndt.Timetable.timetable

import de.parndt.Timetable.utils.Logger
import de.parndt.Timetable.utils.Utils
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimetableUseCase @Inject constructor(
    private val timetableParser: TimetableParser
) {

    fun getLectures(): List<LectureDay> {
        val listLectureDay: MutableList<LectureDay> = mutableListOf()
        val mapDateLecutre = timetableParser.getLectures().groupBy { it.date }

        mapDateLecutre.forEach { (date, lecturesList) ->
            listLectureDay.add(getTypeOfLecture(date, lecturesList))
        }

        listLectureDay.sortBy { it.getDateValue() }
        return listLectureDay
    }

    private fun getTypeOfLecture(date: String, lectures: List<Lecture>): LectureDay {
        val dateObject = parseStringToDate(date)
        val formatedDate = formatStringDate(date)
        return when {
            dateObject.isBefore(getCurrentDate()) -> {
                PastLectureDay(formatedDate, lectures)
            }

            dateObject.isEqual(getCurrentDate()) -> {
                CurrentLectureDay(formatedDate, lectures)
            }

            dateObject.isAfter(getCurrentDate()) -> {
                DefaultLectureDay(formatedDate, lectures)
            }
            else -> {
                Logger.error("Date is not in our timeline 0.o")
                DefaultLectureDay(formatedDate, lectures)
            }
        }
    }

    fun getCurrentDate(): LocalDate {
        return LocalDate.now()
    }

    private fun parseStringToDate(date: String): LocalDate {
        return LocalDate.parse(date, Utils.dateFormater("dd.MM.yy"))
    }

    private fun parseDateToString(date: LocalDate): String {
        return date.format(Utils.dateFormater("EE dd.MM.yy"))
    }

    private fun formatStringDate(date: String): String {
        val date = parseStringToDate(date)
        return date.format(Utils.dateFormater("EE dd.MM.yy"))
    }
}
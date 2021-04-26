/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.timetable.daytimetable

import de.parndt.timetable.database.models.LectureEntity
import de.parndt.timetable.database.repository.LectureRepository
import de.parndt.timetable.general.lecture.LectureDay
import de.parndt.timetable.general.loader.TimetableLoader
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

class DayTimetableUseCase @Inject constructor(
    private val timetableLoader: TimetableLoader,
    private val lectureRepository: LectureRepository
) {

    private suspend fun getLectures(): List<LectureDay> {
        val lectures: List<LectureEntity> = if (lecturesInDatabase()) {
            loadLecturesFromDatabase()
        } else {
            timetableLoader.loadAndSaveLectures()
        }

        return lectures
            .groupBy { it.date }
            .map { (date, lectureDay) ->
                LectureDay(date, lectureDay)
            }.sortedBy {
                it.getDateValue()
            }
    }

    private fun lecturesInDatabase(): Boolean {
        return !lectureRepository.isTableEmpty()
    }

    private fun loadLecturesFromDatabase(): List<LectureEntity> {
        return lectureRepository.getAllLectures()
    }

    suspend fun getTodayLectures(): LectureDay? {
        val lectures = getLectures()
        return lectures.find { it.getDateValue() == getCurrentDate() }
    }

    private fun getCurrentDate(): LocalDate {
        return LocalDate.now(ZoneId.of("Europe/Paris"))
    }

}
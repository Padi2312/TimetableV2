/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.timetable

import de.parndt.timetable.database.models.LectureEntity
import de.parndt.timetable.general.lecture.LectureDay
import de.parndt.timetable.general.loader.TimetableLoader
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimetableUseCase @Inject constructor(
    private val timetableLoader: TimetableLoader
) {

    suspend fun getLectures(): List<LectureDay> {
        val lectures: List<LectureEntity> = timetableLoader.loadAndSaveLectures()

        return lectures
            .groupBy { it.date }
            .map { (date, lectureDay) ->
                LectureDay(date, lectureDay)
            }.sortedBy {
                it.getDateValue()
            }
    }

    suspend fun refreshLectures(): List<LectureDay> {
        val lectures = timetableLoader.loadAndSaveLectures(true)
        return lectures
            .groupBy { it.date }
            .map { (date, lectureDay) ->
                LectureDay(date, lectureDay)
            }.sortedBy {
                it.getDateValue()
            }
    }

    fun getCurrentDate(): LocalDate {
        return LocalDate.now(ZoneId.of("Europe/Paris"))
    }

}
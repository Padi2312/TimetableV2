/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.parndt.timetable.utils.Utils
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Entity(tableName = "lectures")
class LectureEntity(name: String, date: String, time: String, course: String, room: String) {

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo
    var name: String = name

    @ColumnInfo
    var date: String = date

    @ColumnInfo
    var time: String = time

    @ColumnInfo
    var course: String = course

    @ColumnInfo
    var room: String = room

    private fun getStartEndTime(): Pair<LocalDateTime, LocalDateTime> {
        val startTimeString = time.subSequence(0, 5)
        val endTimeString = time.subSequence(5 + 3, time.length)

        val dateOnly = date.substring(0, 10)

        val startDate = LocalDateTime.parse(
            "$dateOnly $startTimeString", Utils.dateFormater("yyyy-MM-dd HH:mm")
        )

        val endDate = LocalDateTime.parse(
            "$dateOnly $endTimeString", Utils.dateFormater("yyyy-MM-dd HH:mm")
        )

        return Pair(startDate, endDate)
    }

    fun getProgress(): Int {
        val startEndTime = getStartEndTime()
        val currentDate = Utils.dateTimeNow()

        if (currentDate.isBefore(startEndTime.first)) {
            return 0
        } else {
            val minutesOfLectures =
                ChronoUnit.MINUTES.between(startEndTime.first, startEndTime.second)
            val minutesPassed = ChronoUnit.MINUTES.between(currentDate, startEndTime.second)

            if (minutesPassed < 0 || minutesPassed == minutesOfLectures) {
                return 100
            } else {
                return ((minutesOfLectures / 100) * minutesPassed).toInt()
            }
        }
    }

    open fun getDateTimeValue(): LocalDateTime {
        return LocalDateTime.parse(date, Utils.dateFormater("yyyy-MM-dd HH:mm:ss"))
    }

}
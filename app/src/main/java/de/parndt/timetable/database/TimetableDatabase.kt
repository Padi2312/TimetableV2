/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.parndt.timetable.database.daos.CourseDao
import de.parndt.timetable.database.daos.LectureDao
import de.parndt.timetable.database.entities.CourseEntity
import de.parndt.timetable.database.entities.LectureEntity


@Database(
    entities = [
        LectureEntity::class,
        CourseEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TimetableDatabase : RoomDatabase() {
    abstract fun lectureDao(): LectureDao
    abstract fun courseDao(): CourseDao

}
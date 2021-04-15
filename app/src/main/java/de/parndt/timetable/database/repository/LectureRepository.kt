/*
 * *
 *  * Created by Patrick Arndt on 2020
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 09.11.20 19:33
 *
 */

package de.parndt.timetable.database.repository

import de.parndt.timetable.database.TimetableDatabase
import de.parndt.timetable.database.models.LectureEntity
import de.parndt.timetable.database.persistence.LectureDao
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LectureRepository @Inject constructor(
    database: TimetableDatabase
) {

    private var lectureDao: LectureDao = database.lectureDao()

    suspend fun insertLecture(lecture: LectureEntity): Long {
        return lectureDao.insertLecture(lecture)
    }

    suspend fun deleteAllLectures() {
        lectureDao.deleteAllLectures()
    }

    suspend fun insertLecture(lecture: List<LectureEntity>): List<Long> {
        return lectureDao.insertLecture(lecture)
    }

    fun getAllLectures(): MutableList<LectureEntity> {
        return lectureDao.getAll()
    }

    fun isTableEmpty(): Boolean {
        return lectureDao.count() == 0
    }
}


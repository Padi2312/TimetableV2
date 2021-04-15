/*
 * *
 *  * Created by Patrick Arndt on 29.10.20 19:49
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 29.10.20 19:46
 *
 */

package de.parndt.timetable.database.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.parndt.timetable.database.models.LectureEntity

@Dao
interface LectureDao {

    @Query("SELECT * FROM lectures")
    fun getAll(): MutableList<LectureEntity>

    @Insert
    suspend fun insertLecture(lecture: LectureEntity): Long

    @Insert
    suspend fun insertLecture(lectureList: List<LectureEntity>): List<Long>

    @Query("DELETE FROM lectures")
    suspend fun deleteAllLectures()


    @Query("SELECT count(*) FROM lectures")
    fun count(): Int

}
/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.parndt.timetable.database.entities.LectureEntity

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
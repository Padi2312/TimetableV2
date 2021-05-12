/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.parndt.timetable.database.entities.CourseEntity

@Dao
interface CourseDao {

    @Query("SELECT * FROM course")
    fun getAll(): List<CourseEntity>

    @Query("SELECT * FROM course WHERE name == :courseName")
    fun getCourseByName(courseName: String): CourseEntity

    @Query("SELECT count(*) FROM lectures")
    fun count(): Int

    @Insert
    suspend fun insertCourse(courseList: List<CourseEntity>): List<Long>

}
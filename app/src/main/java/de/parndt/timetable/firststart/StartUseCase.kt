/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.firststart

import de.parndt.timetable.database.entities.CourseEntity
import de.parndt.timetable.database.repositories.CourseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StartUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {

    fun getCourseNames(): List<String> {
        return courseRepository.getAllCourseNames()
    }

    fun getCourseByName(courseName: String): CourseEntity {
        return courseRepository.getCourseByName(courseName)
    }
}
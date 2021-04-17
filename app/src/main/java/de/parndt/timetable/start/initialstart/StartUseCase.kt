/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.start.initialstart

import de.parndt.timetable.database.models.CourseEntity
import de.parndt.timetable.database.repository.CourseRepository
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
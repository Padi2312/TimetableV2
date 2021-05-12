/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.general

import android.content.SharedPreferences
import de.parndt.timetable.BuildConfig
import de.parndt.timetable.database.entities.CourseEntity
import de.parndt.timetable.utils.Logger
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Config @Inject constructor(private val sharedPreferences: SharedPreferences) {

    private val COURSE_NAME = "COURSE"
    private val COURSE_URL = "COURSE_URL"
    private val VERSION_CODE = "VERSION_CODE"
    private val LAST_UPDATED = "LAST_UPDATED"
    private val DOESNT_EXIST = -1
    private val currentVersionCode = BuildConfig.VERSION_CODE


    fun setLastUpdated(date: String) {
        with(sharedPreferences.edit()) {
            putString(LAST_UPDATED,date)
            apply()
        }
    }

    fun getLastUpdated(): String {
        return sharedPreferences.getString(LAST_UPDATED, "") ?: ""
    }


    fun setCourse(course: CourseEntity) {
        with(sharedPreferences.edit()) {
            putString(COURSE_NAME, course.name)
            putString(COURSE_URL, course.csvUrl)
            apply()
        }
    }

    fun getCourse(): CourseEntity {
        val courseName = sharedPreferences.getString(COURSE_NAME, "") ?: ""
        val courseUrl = sharedPreferences.getString(COURSE_URL, "") ?: ""
        return CourseEntity(courseName, courseUrl)
    }

    fun resetFirstRunModifications(){
        setVersionCode(DOESNT_EXIST)
    }

    private fun setVersionCodeToCurrent() {
        setVersionCode(currentVersionCode)
    }

    private fun setVersionCode(versionCode:Int){
        with(sharedPreferences.edit()) {
            putInt(VERSION_CODE, versionCode)
            commit()
        }
    }

    fun getAppRunType(): RunType {
        val savedVersionCode = sharedPreferences.getInt(VERSION_CODE, DOESNT_EXIST)
        val typeOfStart = when {
            currentVersionCode == savedVersionCode -> {
                RunType.NORMAL
            }
            savedVersionCode == DOESNT_EXIST -> {
                RunType.FIRST_START
            }
            currentVersionCode > savedVersionCode -> {
                RunType.UPGRADE
            }
            else -> {
                Logger.error("Well something went wrong on checking the first start")
                RunType.NORMAL
            }
        }

        setVersionCodeToCurrent()
        return typeOfStart
    }

}

enum class RunType {
    FIRST_START,
    UPGRADE,
    NORMAL
}
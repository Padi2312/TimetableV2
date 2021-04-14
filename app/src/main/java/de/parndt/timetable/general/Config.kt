package de.parndt.timetable.general

import android.content.SharedPreferences
import de.parndt.timetable.BuildConfig
import de.parndt.timetable.utils.Logger
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Config @Inject constructor(private val sharedPreferences: SharedPreferences) {

    private val COURSE_NAME = "COURSE"
    private val VERSION_CODE = "VERSION_CODE"
    private val DOESNT_EXIST = -1
    private val currentVersionCode = BuildConfig.VERSION_CODE


    fun setCourse(course: String) {
        with(sharedPreferences.edit()) {
            putString(COURSE_NAME, course)
            apply()
        }
    }

    fun getCourse(): String {
        return sharedPreferences.getString(COURSE_NAME, "") ?: ""
    }

    private fun setVersionCodeToCurrent() {
        with(sharedPreferences.edit()) {
            putInt(VERSION_CODE, currentVersionCode)
            commit()
        }
    }

    fun isFirstRun(): Run {
        val savedVersionCode = sharedPreferences.getInt(VERSION_CODE, DOESNT_EXIST)
        val typeOfStart = when {
            currentVersionCode == savedVersionCode -> {
                Run.NORMAL
            }
            savedVersionCode == DOESNT_EXIST -> {
                Run.FIRST_START
            }
            currentVersionCode > savedVersionCode -> {
                Run.UPGRADE
            }
            else -> {
                Logger.error("Well something went wrong on checking the first start")
                Run.NORMAL
            }
        }

        setVersionCodeToCurrent()
        return typeOfStart
    }

}

enum class Run {
    FIRST_START,
    UPGRADE,
    NORMAL
}
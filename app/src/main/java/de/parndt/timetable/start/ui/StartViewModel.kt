package de.parndt.timetable.start.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.parndt.timetable.general.Config
import de.parndt.timetable.general.TimetableParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val timetableParser: TimetableParser,
    private val config: Config
) : ViewModel() {

    private val _courses = MutableLiveData<List<String>>()
    fun getCourses(): LiveData<List<String>> = _courses


    fun loadCourseList() {
        GlobalScope.launch(Dispatchers.IO) {
            val courses = timetableParser.getCourses().toMutableList()
            courses.add(0,"")
            courses.sortedDescending()
            _courses.postValue(courses)
        }
    }

    fun setCourse(course: String) {
        config.setCourse(course)
    }

    fun isCourseSet(): Boolean {
        return config.getCourse().isNotEmpty()
    }

    class Factory @Inject constructor(
        private val timetableParser: TimetableParser,
        private val config: Config
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return StartViewModel(timetableParser, config) as T
        }
    }
}
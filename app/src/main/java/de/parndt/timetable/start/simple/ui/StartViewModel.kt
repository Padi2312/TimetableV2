/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.start.simple.ui

import androidx.lifecycle.*
import de.parndt.timetable.general.Config
import de.parndt.timetable.start.simple.StartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val startUseCase: StartUseCase,
    private val config: Config
) : ViewModel() {

    private val _courses = MutableLiveData<List<String>>()
    fun getCourseNames(): LiveData<List<String>> = _courses

    fun loadCourseNames() {
        viewModelScope.launch(Dispatchers.IO) {
            val courseNames = startUseCase.getCourseNames().toMutableList()
            courseNames.add(0, "")
            courseNames.sortedDescending()
            _courses.postValue(courseNames)
        }
    }


    fun setCourse(course: String, callback: (() -> Unit)) {
        viewModelScope.launch(Dispatchers.IO) {
            val course = startUseCase.getCourseByName(course)
            config.setCourse(course)
            callback.invoke()
        }
    }

    fun isCourseSet(): Boolean {
        return config.getCourse().name.isNotEmpty()
    }

    class Factory @Inject constructor(
        private val startUseCase: StartUseCase,
        private val config: Config
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return StartViewModel(startUseCase, config) as T
        }
    }
}
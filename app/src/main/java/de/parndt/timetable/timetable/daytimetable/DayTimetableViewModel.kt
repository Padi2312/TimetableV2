/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.timetable.daytimetable

import androidx.lifecycle.*
import de.parndt.timetable.general.lecture.LectureDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import javax.inject.Inject

class DayTimetableViewModel @Inject constructor(
    private val useCase: DayTimetableUseCase
) : ViewModel() {

    val todaysLecture: MutableLiveData<LectureDay?> = MutableLiveData()

    fun getTodaysLectureDay(): LiveData<LectureDay?> {
        viewModelScope.launch(Dispatchers.IO) {
            val todaysLectureResult = useCase.getTodayLectures()
            todaysLecture.postValue(todaysLectureResult)
        }
        return todaysLecture
    }


    class Factory @Inject constructor(
        private val useCase: DayTimetableUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DayTimetableViewModel(useCase) as T
        }
    }
}
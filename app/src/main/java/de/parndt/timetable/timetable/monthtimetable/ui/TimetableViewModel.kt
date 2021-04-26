/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.timetable.monthtimetable.ui

import androidx.lifecycle.*
import de.parndt.timetable.general.lecture.LectureDay
import de.parndt.timetable.timetable.monthtimetable.TimetableUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

class TimetableViewModel @Inject constructor(
    private val useCase: TimetableUseCase
) : ViewModel() {

    private val _lectures = MutableLiveData<List<LectureDay>>()
    fun getLectures(): LiveData<List<LectureDay>> = _lectures

    fun loadLectures() {
        viewModelScope.launch(Dispatchers.IO) {
            _lectures.postValue(useCase.getLectures())
        }
    }

    fun getCurrentDate(): LocalDate {
        return useCase.getCurrentDate()
    }

    fun refreshLectures() {
        viewModelScope.launch(Dispatchers.IO) {
            _lectures.postValue(useCase.refreshLectures())
        }
    }

    class Factory @Inject constructor(
        private val useCase: TimetableUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TimetableViewModel(useCase) as T
        }
    }
}
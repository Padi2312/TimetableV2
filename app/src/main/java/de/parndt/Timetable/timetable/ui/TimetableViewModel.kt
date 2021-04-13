package de.parndt.Timetable.timetable.ui

import androidx.lifecycle.*
import de.parndt.Timetable.timetable.LectureDay
import de.parndt.Timetable.timetable.TimetableUseCase
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

    class Factory @Inject constructor(
        private val useCase: TimetableUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TimetableViewModel(useCase) as T
        }
    }
}
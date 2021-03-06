/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.init

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.parndt.timetable.general.Config
import de.parndt.timetable.general.RunType
import de.parndt.timetable.utils.NetworkConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class InitViewModel @Inject constructor(
    private val initUseCase: InitUseCase,
    private val config: Config,
    private val context: Context
) : ViewModel() {


    private val initState = MutableLiveData<EInitState>()
    fun getInitState(): LiveData<EInitState> = initState


    /**
     * Returns the RunType of the App
     * @return RunType {}
     *
     */
    fun getAppRunType(): RunType {
        return config.getAppRunType()
    }

    /**
     * If init gets canceled the app should start init process on the next start
     * The version code gets deleted in shared prefs,
     * that the init process starts again
     */
    fun cancelInit() {
        config.resetFirstRunModifications()
    }


    /**
     * Check if network connection is available. If N/A the app is going to be closed
     * Function should only be called on first start!
     * @return true if network connection is available otherwise false
     */
    fun checkNetworkConnectionOnFirstStart(): Boolean {
        return NetworkConnection.isOnline(context)
    }

    fun loadCourses() {
        GlobalScope.launch(Dispatchers.IO) {
            initUseCase.loadAndSaveCourses()
            initState.postValue(EInitState.FIRST_INIT)
        }
    }

    fun loadLectures() {
        GlobalScope.launch(Dispatchers.IO) {
            initUseCase.loadAndSaveLectures()
            initState.postValue(EInitState.DEFAULT_INIT)
        }
    }


    class Factory @Inject constructor(
        private val initUseCase: InitUseCase,
        private val config: Config,
        private val context: Context
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return InitViewModel(initUseCase, config, context) as T
        }
    }
}
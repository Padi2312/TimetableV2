/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class SharedPrefsModule {

    private val SHARED_PREFS_NAME = "CONFIG"

    @Provides
    fun provideSharedPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

}
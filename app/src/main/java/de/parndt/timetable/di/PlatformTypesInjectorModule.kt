/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.parndt.timetable.MainActivity
import de.parndt.timetable.di.fragmentmodules.DayTimetableFragmentModule
import de.parndt.timetable.di.fragmentmodules.InitFragmentModule
import de.parndt.timetable.di.fragmentmodules.StartFragmentModule
import de.parndt.timetable.di.fragmentmodules.TimetableFragmentModule
import de.parndt.timetable.di.modules.MainActivityModule
import de.parndt.timetable.firststart.StartFragment
import de.parndt.timetable.init.InitFragment
import de.parndt.timetable.timetable.daytimetable.ui.DayTimetableFragment
import de.parndt.timetable.timetable.monthtimetable.ui.TimetableFragment


@Module
@Suppress("unused")
abstract class PlatformTypesInjectorModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [TimetableFragmentModule::class])
    abstract fun contributeTimetableFragment(): TimetableFragment

    @ContributesAndroidInjector(modules = [StartFragmentModule::class])
    abstract fun contributeStartFragment(): StartFragment

    @ContributesAndroidInjector(modules = [InitFragmentModule::class])
    abstract fun contributeInitFragment(): InitFragment

    @ContributesAndroidInjector(modules = [DayTimetableFragmentModule::class])
    abstract fun contributeDayTimetableFragment(): DayTimetableFragment
}


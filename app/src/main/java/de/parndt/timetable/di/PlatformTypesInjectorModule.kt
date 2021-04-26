/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.parndt.timetable.start.init.di.InitFragmentModule
import de.parndt.timetable.start.init.ui.InitFragment
import de.parndt.timetable.start.simple.di.StartFragmentModule
import de.parndt.timetable.start.simple.ui.StartFragment
import de.parndt.timetable.timetable.daytimetable.DayTimetableFragment
import de.parndt.timetable.timetable.daytimetable.di.DayTimetableFragmentModule
import de.parndt.timetable.timetable.monthtimetable.di.TimetableFragmentModule
import de.parndt.timetable.timetable.monthtimetable.ui.TimetableFragment
import de.parndt.timetable.ui.MainActivity


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


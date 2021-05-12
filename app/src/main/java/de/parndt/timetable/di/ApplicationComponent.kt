/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import de.parndt.timetable.TimetableApplication
import de.parndt.timetable.di.modules.MainActivityModule
import de.parndt.timetable.di.modules.NetworkModule
import de.parndt.timetable.di.modules.RoomDatabaseModule
import de.parndt.timetable.di.modules.SharedPrefsModule
import javax.inject.Named
import javax.inject.Singleton

@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    PlatformTypesInjectorModule::class,
    RoomDatabaseModule::class,
    MainActivityModule::class,
    SharedPrefsModule::class,
    NetworkModule::class
])
@Singleton
interface ApplicationComponent:AndroidInjector<TimetableApplication> {

    override fun inject(timetableApplication: TimetableApplication)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(context:Context):Builder

        @BindsInstance
        fun networkModule(@Named("baseUrl")baseUrl:String):Builder

        fun roomDatabaseModule(roomDatabaseModule: RoomDatabaseModule):Builder

        fun build():ApplicationComponent
    }
}
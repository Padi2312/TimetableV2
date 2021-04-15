package de.parndt.timetable

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasAndroidInjector
import de.parndt.timetable.database.di.RoomDatabaseModule
import de.parndt.timetable.di.ApplicationComponent
import de.parndt.timetable.di.DaggerApplicationComponent

class TimetableApplication : DaggerApplication(), HasAndroidInjector {

    lateinit var applicationComponent: ApplicationComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent =
            DaggerApplicationComponent.builder().roomDatabaseModule(RoomDatabaseModule((this)))
                .networkModule("").context(this).build()
        return applicationComponent
    }

}
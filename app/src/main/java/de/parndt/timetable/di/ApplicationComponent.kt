package de.parndt.timetable.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import de.parndt.timetable.TimetableApplication
import de.parndt.timetable.database.di.RepositoryModule
import de.parndt.timetable.database.di.RoomDatabaseModule
import javax.inject.Named
import javax.inject.Singleton

@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    PlatformTypesInjectorModule::class,
    RepositoryModule::class,
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
package de.parndt.timetable.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
class CourseEntity(name: String, csvUrl: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Int? = null

    @ColumnInfo
    var name: String = name

    @ColumnInfo
    var csvUrl: String = csvUrl

}
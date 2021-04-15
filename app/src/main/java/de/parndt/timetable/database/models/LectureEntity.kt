package de.parndt.timetable.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lectures")
class LectureEntity(name: String, date: String, time: String, course: String, room: String) {

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo
    var name: String = name

    @ColumnInfo
    var date: String = date

    @ColumnInfo
    var time: String = time

    @ColumnInfo
    var course: String = course

    @ColumnInfo
    var room: String = room
}
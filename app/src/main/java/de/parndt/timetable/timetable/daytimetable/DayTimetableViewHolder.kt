/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.timetable.daytimetable

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import de.parndt.timetable.database.models.LectureEntity
import kotlinx.android.synthetic.main.list_item_lecture_day_view.view.*


class DayTimetableViewHolder(view: View, private val context: Context) :
    RecyclerView.ViewHolder(view) {

    fun bind(item: LectureEntity) {

        itemView.dayLectureName.text = item.name
        itemView.dayLectureTime.text = item.time

        if (item.room.isNotEmpty()) {
            itemView.dayLectureRoom.text = item.room
        } else {
            itemView.dayLectureRoom.visibility = View.GONE
        }

        Handler(Looper.getMainLooper()).postDelayed({
            itemView.dayLectureProgress.setProgressCompat(item.getProgress(), true)
        }, 500)

    }
}

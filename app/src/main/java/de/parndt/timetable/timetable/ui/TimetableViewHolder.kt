/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.timetable.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.parndt.timetable.R
import de.parndt.timetable.general.lecture.LectureDay
import de.parndt.timetable.utils.Utils
import kotlinx.android.synthetic.main.list_item_daily_lecture.view.*

open class TimetableViewHolder(view: View, private val context: Context) :
    RecyclerView.ViewHolder(view) {

    fun bind(item: LectureDay) {
        itemView.defaultLectureDate.text = item.Date

        itemView.defaultLecturesOfDay?.removeAllViews()

        if (item.getDateValue() == Utils.dateNow()) {
            itemView.dailyLecturesView.strokeWidth = 10
        } else {
            itemView.dailyLecturesView.strokeWidth = 0
        }

        if (item.getDateValue().isBefore(Utils.dateNow())) {
            itemView.defaultLectureOverlay.visibility = View.VISIBLE
        } else {
            itemView.defaultLectureOverlay.visibility = View.GONE
        }


        val lecturesOfDay = item.lecturesList
        for (i in lecturesOfDay.indices) {

            val lecturesView = LayoutInflater.from(context)
                .inflate(R.layout.list_item_lecture, null, true)

            lecturesView.findViewById<TextView>(R.id.lectureName).text = lecturesOfDay[i].name
            lecturesView.findViewById<TextView>(R.id.lectureTime).text = lecturesOfDay[i].time

            itemView.defaultLecturesOfDay.addView(lecturesView)

            if (i != lecturesOfDay.size - 1) {
                itemView.defaultLecturesOfDay.addView(getSeparatorView())
            }
        }


    }

    private fun getSeparatorView(): View {
        val seperator = View(context)
        seperator.setBackgroundColor(Color.GRAY)
        seperator.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2)
        return seperator
    }

}

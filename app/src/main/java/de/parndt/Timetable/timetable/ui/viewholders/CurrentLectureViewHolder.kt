package de.parndt.Timetable.timetable.ui.viewholders

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.parndt.Timetable.R
import de.parndt.Timetable.timetable.LectureDay
import kotlinx.android.synthetic.main.list_item_current_lecture.view.*
import java.time.LocalDate

class CurrentLectureViewHolder private constructor(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {

    fun bind(item: LectureDay) {
        itemView.currentLectureDate.text = item.date

        itemView.currentLecturesOfDay?.removeAllViews()

        if (item.getDateValue() == LocalDate.now())
            itemView.dailyLecturesView.strokeWidth = 10


        val lecturesOfDay = item.lecturesList
        for (i in lecturesOfDay.indices) {

            val lecturesView = LayoutInflater.from(context)
                .inflate(R.layout.list_item_lecture, null, true)

            lecturesView.findViewById<TextView>(R.id.lectureName).text = lecturesOfDay[i].name
            lecturesView.findViewById<TextView>(R.id.lectureTime).text = lecturesOfDay[i].time

            itemView.currentLecturesOfDay.addView(lecturesView)

            if (i != lecturesOfDay.size - 1) {
                itemView.currentLecturesOfDay.addView(getSeparatorView())
            }
        }


    }

    private fun getSeparatorView(): View {
        val seperator = View(context)
        seperator.setBackgroundColor(Color.GRAY)
        seperator.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2)
        return seperator
    }

    companion object {
        fun from(parent: ViewGroup, context: Context): CurrentLectureViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_current_lecture, parent, false)

            return CurrentLectureViewHolder(
                view,
                context
            )
        }
    }
}
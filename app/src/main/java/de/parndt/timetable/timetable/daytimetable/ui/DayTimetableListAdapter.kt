/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.timetable.daytimetable.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import de.parndt.timetable.R
import de.parndt.timetable.database.entities.LectureEntity

class DayTimetableListAdapter(val _context: Context) :
    ListAdapter<LectureEntity, DayTimetableViewHolder>(LecturesListDiffCallback) {


    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayTimetableViewHolder {
        return DayTimetableViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_lecture_day_view, parent, false), _context
        )
    }

    override fun onBindViewHolder(holder: DayTimetableViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


object LecturesListDiffCallback : DiffUtil.ItemCallback<LectureEntity>() {
    override fun areItemsTheSame(oldItem: LectureEntity, newItem: LectureEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LectureEntity, newItem: LectureEntity): Boolean {
        return oldItem.id == newItem.id
    }
}

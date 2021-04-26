/*
 * Copyright (c) 2021 Patrick Arndt
 */

package de.parndt.timetable.timetable.monthtimetable.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import de.parndt.timetable.R
import de.parndt.timetable.general.lecture.LectureDay
import java.time.LocalDate


class TimetableListAdapter(val _context: Context) :
    ListAdapter<LectureDay, TimetableViewHolder>(LecturesDayListDiffCallback) {


    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimetableViewHolder {
        return TimetableViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_daily_lecture, parent, false), _context
        )
    }

    override fun onBindViewHolder(holder: TimetableViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    fun getPositionOfItemByDate(date: LocalDate): Int {
        return this.currentList.indexOfFirst { it.getDateValue() == date }
    }
}

object LecturesDayListDiffCallback : DiffUtil.ItemCallback<LectureDay>() {
    override fun areItemsTheSame(oldItem: LectureDay, newItem: LectureDay): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LectureDay, newItem: LectureDay): Boolean {
        return oldItem.id == newItem.id
    }
}

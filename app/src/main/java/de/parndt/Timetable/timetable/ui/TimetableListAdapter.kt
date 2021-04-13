package de.parndt.Timetable.timetable.ui

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.parndt.Timetable.timetable.CurrentLectureDay
import de.parndt.Timetable.timetable.DefaultLectureDay
import de.parndt.Timetable.timetable.LectureDay
import de.parndt.Timetable.timetable.PastLectureDay
import de.parndt.Timetable.timetable.ui.viewholders.CurrentLectureViewHolder
import de.parndt.Timetable.timetable.ui.viewholders.DefaultLectureViewHolder
import de.parndt.Timetable.timetable.ui.viewholders.PastLectureViewHolder
import de.parndt.Timetable.utils.Logger
import java.time.LocalDate


const val currentLecturesDay = 0
const val pastLecturesDay = 1
const val defaultDay = 2

class TimetableListAdapter(val _context: Context) :
    ListAdapter<LectureDay, RecyclerView.ViewHolder>(LecturesListDiffCallback) {

    private var lecturesList: List<LectureDay> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            currentLecturesDay -> {
                CurrentLectureViewHolder.from(parent, _context)
            }
            pastLecturesDay -> {
                PastLectureViewHolder.from(parent, _context)
            }
            defaultDay -> {
                DefaultLectureViewHolder.from(parent, _context)
            }
            else -> {
                Logger.error("No view type found for $viewType")
                DefaultLectureViewHolder.from(parent, _context)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is CurrentLectureDay -> {
                (holder as CurrentLectureViewHolder).bind(item)
            }
            is PastLectureDay -> {
                (holder as PastLectureViewHolder).bind(item)
            }
            is DefaultLectureDay -> {
                (holder as DefaultLectureViewHolder).bind(item)
            }
            else -> {
                Logger.error("No view holder found for $item")
                (holder as DefaultLectureViewHolder).bind(item)
            }
        }
    }

    override fun submitList(list: List<LectureDay>?) {
        super.submitList(list)
        lecturesList = list ?: mutableListOf()
    }


    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return when (super.getItem(position)) {
            is CurrentLectureDay -> {
                currentLecturesDay
            }
            is DefaultLectureDay -> {
                defaultDay
            }
            is PastLectureDay -> {
                pastLecturesDay
            }
            else -> {
                Logger.error("No item view type found for position $position")
                defaultDay
            }
        }
    }

    fun getPositionOfItemByDate(date: LocalDate): Int {
        return this.currentList.indexOfFirst { it.getDateValue() == date }
    }
}

object LecturesListDiffCallback : DiffUtil.ItemCallback<LectureDay>() {
    override fun areItemsTheSame(oldItem: LectureDay, newItem: LectureDay): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LectureDay, newItem: LectureDay): Boolean {
        return oldItem.id == newItem.id
    }
}

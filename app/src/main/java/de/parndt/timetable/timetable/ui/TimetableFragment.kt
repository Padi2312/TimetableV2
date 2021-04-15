package de.parndt.timetable.timetable.ui

import android.content.Context
import android.os.Bundle
import android.util.TimingLogger
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import de.parndt.timetable.R
import de.parndt.timetable.general.loader.TimetableLoader
import de.parndt.timetable.utils.Logger
import kotlinx.android.synthetic.main.fragment_timetable.*
import javax.inject.Inject
import kotlin.math.pow
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class TimetableFragment : Fragment() {

    @Inject
    lateinit var viewModel: TimetableViewModel
    private lateinit var adapter: TimetableListAdapter

    private var startTime = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timetable, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @ExperimentalTime
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = TimetableListAdapter(requireContext())
        lecturesList.adapter = adapter
        lecturesList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        viewModel.getLectures().observe(viewLifecycleOwner) {
            val timenow = System.nanoTime()
            Logger.info("Time elapsed: ${(timenow-startTime)/10.toFloat().pow(6) } ms")

            hideLoadingIndicator()
            adapter.submitList(it)
            scrollToCurrentDay()

        }
        startTime = System.nanoTime()
        viewModel.loadLectures()


    }

    private fun hideLoadingIndicator() {
        timetableLoadingIndicator.visibility = View.GONE
    }

    private fun scrollToCurrentDay() {
        val positon = adapter.getPositionOfItemByDate(viewModel.getCurrentDate())
        lecturesList?.scrollToPosition(positon)
    }

}
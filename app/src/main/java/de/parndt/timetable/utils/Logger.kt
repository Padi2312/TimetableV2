package de.parndt.timetable.utils

import android.util.Log
import kotlin.math.pow

object Logger {

    private val tag = "Timetable"
    private var times: MutableList<Pair<String, Long>> = mutableListOf()

    fun error(message: String) {
        error(Exception(message))
    }

    fun error(ex: Throwable) {
        Log.e(tag, ex.localizedMessage, ex)
    }

    fun info(message: String) {
        Log.i(tag, message)
    }

    fun startTime(info: String) {
        times.add(Pair(info, System.nanoTime()))
    }

    fun endTime(info: String) {
        val endTime = System.nanoTime()
        val startTimePair = times.find { it.first == info } ?: return

        val time = (endTime - startTimePair.second) / 10.toFloat().pow(6)
        times.remove(startTimePair)

        Log.d(tag, "$info: $time ms")
    }
}
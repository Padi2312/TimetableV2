package de.parndt.timetable.general.loader

import de.parndt.timetable.database.models.CourseEntity
import de.parndt.timetable.database.models.LectureEntity
import de.parndt.timetable.database.repository.CourseRepository
import de.parndt.timetable.database.repository.LectureRepository
import de.parndt.timetable.general.CSVParser
import de.parndt.timetable.general.Config
import de.parndt.timetable.general.lecture.LectureCSVModel
import de.parndt.timetable.utils.Logger
import de.parndt.timetable.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimetableLoader @Inject constructor(
    private val httpClient: OkHttpClient,
    private val config: Config,
    private val lectureRepository: LectureRepository,
    private val courseRepository: CourseRepository
) {

    var courseModelList: List<CourseEntity> = listOf()
    var lectureModelList: List<LectureEntity> = listOf()
    val raplaCSVLink =
        "https://rapla.dhbw.de/rapla/calendar.csv?key=BX2MrCsUIl-Bm8MTaxjslJ5wzIryM3bkwiDCe4PaYyfZXI0yz1sCIhXdyNtXoZmA4dkOgdRA7EzUF6DPupMNP-fIrgQkDVd99rZKEbgqKhajWy7WGDsHbMqAunKlxm8EGryjvwt1kpad5g93Dkdn0A&salt=-668364712&allocatable_id="
    val raplaLink =
        "https://rapla.dhbw.de/rapla/calendar?key=BX2MrCsUIl-Bm8MTaxjslJ5wzIryM3bkwiDCe4PaYyfZXI0yz1sCIhXdyNtXoZmA4dkOgdRA7EzUF6DPupMNP-fIrgQkDVd99rZKEbgqKhajWy7WGDsHbMqAunKlxm8EGryjvwt1kpad5g93Dkdn0A&salt=-668364712"


    private fun loadTimetable(): List<LectureCSVModel>? {
        val csvURL = config.getCourse().csvUrl
        if (csvURL.isEmpty()) {
            Logger.error("csvURL was empty for course ${config.getCourse().name}")
            return null
        }

        val request = Utils.reqeust(csvURL)
        httpClient.newCall(request).execute().use { response ->

            if (!response.isSuccessful) {
                Logger.error("Download Timetable request was not successful. Error: $response")
                return null
            }

            val test = response.body!!.string()
            return CSVParser.parseToFromString<LectureCSVModel>(test, ';')
        }
    }

    suspend fun loadAndSaveLectures(): List<LectureEntity>? {
        lectureModelList = lectureRepository.getAllLectures()
        return if (lectureModelList.isNotEmpty()) {
            lectureModelList
        } else {
            val timetable = loadTimetable() ?: return null
            val lectureList = timetable.map { lecture ->
                LectureEntity(
                    lecture.name ?: "",
                    lecture.date ?: "",
                    lecture.times ?: "",
                    lecture.course ?: "",
                    lecture.room ?: ""
                )
            }
            lectureModelList = lectureList
            lectureRepository.insertLecture(lectureModelList)
            lectureModelList
        }
    }

    suspend fun loadAndSaveCourses(): List<CourseEntity> {
        courseModelList = courseRepository.getAllCourses()
        return if (courseModelList.isNotEmpty())
            courseModelList
        else {
            val courseListDocument = Jsoup.connect(raplaLink).get()

            val courseList = mutableListOf<CourseEntity>()
            val courseTableBody = courseListDocument!!.select("table")[1].children().first()

            courseTableBody.children().drop(2).forEach { tr ->
                val courseName = tr.children().first().text()
                val courseFileLink =
                    tr.children()[2].children().first().attr("href").replace("http", "https")

                courseList.add(CourseEntity(courseName, courseFileLink))
            }
            courseModelList = courseList
            courseRepository.insertCourses(courseModelList)
            courseModelList
        }
    }
}
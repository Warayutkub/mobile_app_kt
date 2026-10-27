package com.example.fitmapp.data

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.fitmapp.data.DatabaseHelper.Companion.T_COURSE
import com.example.fitmapp.data.DatabaseHelper.Companion.T_PROGRAM
import com.example.fitmapp.data.DatabaseHelper.Companion.T_STRUCTURE
import com.example.fitmapp.data.DatabaseHelper.Companion.T_STUDY_PLAN
import com.example.fitmapp.data.DatabaseHelper.Companion.T_TERM

/**
 * ทุกหน้าขอข้อมูลหลักสูตรผ่านตัวนี้ตัวเดียว — ข้อมูลอ่านจาก SQLite (DatabaseHelper)
 * อ่านทั้งหมดครั้งแรกที่เรียกแล้วเก็บไว้ในหน่วยความจำ (ข้อมูลไม่กี่ร้อยแถว และไม่มีการแก้ระหว่างใช้แอป)
 *
 * ต้องเรียก init(context) ก่อนใช้ — ทำไว้แล้วใน FitmApp.onCreate
 */
object ProgramRepository {

    private lateinit var helper: DatabaseHelper
    private var cache: List<Program>? = null

    fun init(context: Context) {
        helper = DatabaseHelper(context.applicationContext)
    }

    fun programs(): List<Program> = cache ?: load(helper.readableDatabase).also { cache = it }

    /** degree = "วท.บ." / "วศ.บ." / "อส.บ." หรือ null = ทั้งหมด */
    fun programs(degree: String?): List<Program> =
        if (degree == null) programs() else programs().filter { it.degree == degree }

    fun program(id: String?): Program = programs().firstOrNull { it.id == id } ?: programs().first()

    fun terms(programId: String?): List<Term> = program(programId).terms

    /** ชื่อวิชาจากรหัส (ใช้แสดงวิชาบังคับก่อน) */
    fun courseName(programId: String?, code: String): String? =
        terms(programId).flatMap { it.courses }.firstOrNull { it.code == code }?.nameTh

    // ---------- อ่านจากฐานข้อมูล ----------

    private fun load(db: SQLiteDatabase): List<Program> =
        db.rawQuery("SELECT * FROM $T_PROGRAM ORDER BY sort", null).use { c ->
            buildList {
                while (c.moveToNext()) {
                    val id = c.str("id")
                    add(
                        Program(
                            id = id, degree = c.str("degree"), plan = c.str("plan"), name = c.str("name"),
                            degreeFull = c.str("degree_full"), entry = c.str("entry"), credits = c.int("credits"),
                            feePerTerm = c.int("fee_per_term"), semesters = c.int("semesters"),
                            tone = Tone.valueOf(c.str("tone")), revision = c.str("revision"),
                            structure = structure(db, id), studyPlans = studyPlans(db, id), terms = terms(db, id),
                            placeholder = c.int("placeholder") == 1
                        )
                    )
                }
            }
        }

    private fun structure(db: SQLiteDatabase, programId: String): List<Pair<String, Int>> =
        db.rawQuery("SELECT name, credits FROM $T_STRUCTURE WHERE program_id = ? ORDER BY sort", arrayOf(programId)).use { c ->
            buildList { while (c.moveToNext()) add(c.str("name") to c.int("credits")) }
        }

    private fun studyPlans(db: SQLiteDatabase, programId: String): List<StudyPlan> =
        db.rawQuery("SELECT name, detail FROM $T_STUDY_PLAN WHERE program_id = ? ORDER BY sort", arrayOf(programId)).use { c ->
            buildList { while (c.moveToNext()) add(StudyPlan(c.str("name"), c.str("detail"))) }
        }

    private fun terms(db: SQLiteDatabase, programId: String): List<Term> =
        db.rawQuery("SELECT id, title FROM $T_TERM WHERE program_id = ? ORDER BY sort", arrayOf(programId)).use { c ->
            buildList { while (c.moveToNext()) add(Term(c.str("title"), courses(db, c.long("id")))) }
        }

    private fun courses(db: SQLiteDatabase, termId: Long): List<Course> =
        db.rawQuery("SELECT * FROM $T_COURSE WHERE term_id = ? ORDER BY sort", arrayOf(termId.toString())).use { c ->
            buildList {
                while (c.moveToNext()) add(
                    Course(
                        code = c.str("code"), nameTh = c.str("name_th"), nameEn = c.str("name_en"),
                        credits = c.int("credits"), hours = c.str("hours"), english = c.int("english") == 1,
                        prerequisite = c.str("prerequisite"), descriptionEn = c.str("description_en"),
                        descriptionTh = c.str("description_th")
                    )
                )
            }
        }

    private fun Cursor.str(col: String): String = getString(getColumnIndexOrThrow(col)) ?: ""
    private fun Cursor.int(col: String): Int = getInt(getColumnIndexOrThrow(col))
    private fun Cursor.long(col: String): Long = getLong(getColumnIndexOrThrow(col))
}

package com.example.fitmapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """CREATE TABLE $T_PROGRAM (
                id TEXT PRIMARY KEY, sort INTEGER, degree TEXT, plan TEXT, name TEXT, degree_full TEXT,
                entry TEXT, credits INTEGER, fee_per_term INTEGER, semesters INTEGER, tone TEXT,
                revision TEXT, placeholder INTEGER)"""
        )
        db.execSQL("CREATE TABLE $T_STRUCTURE (program_id TEXT, sort INTEGER, name TEXT, credits INTEGER)")
        db.execSQL("CREATE TABLE $T_STUDY_PLAN (program_id TEXT, sort INTEGER, name TEXT, detail TEXT)")
        db.execSQL("CREATE TABLE $T_TERM (id INTEGER PRIMARY KEY AUTOINCREMENT, program_id TEXT, sort INTEGER, title TEXT)")
        db.execSQL(
            """CREATE TABLE $T_COURSE (
                term_id INTEGER, sort INTEGER, code TEXT, name_th TEXT, name_en TEXT, credits INTEGER,
                hours TEXT, english INTEGER, prerequisite TEXT, description_en TEXT, description_th TEXT)"""
        )
        db.execSQL("CREATE TABLE $T_LEARN (program_id TEXT PRIMARY KEY, intro TEXT, groups_title TEXT)")
        db.execSQL("CREATE TABLE $T_LEARN_GROUP (program_id TEXT, sort INTEGER, name_th TEXT, name_en TEXT, courses TEXT)")
        db.execSQL("CREATE TABLE $T_LEARN_OUTCOME (program_id TEXT, sort INTEGER, text TEXT)")
        db.execSQL("CREATE TABLE $T_CAREER (program_id TEXT, sort INTEGER, name TEXT, icon TEXT)")
        seed(db, ProgramData.programs)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        for (t in listOf(T_CAREER, T_LEARN_OUTCOME, T_LEARN_GROUP, T_LEARN, T_COURSE, T_TERM, T_STUDY_PLAN, T_STRUCTURE, T_PROGRAM)) db.execSQL("DROP TABLE IF EXISTS $t")
        onCreate(db)
    }

    private fun seed(db: SQLiteDatabase, programs: List<Program>) {
        db.beginTransaction()
        try {
            programs.forEachIndexed { p, program ->
                db.insert(T_PROGRAM, null, ContentValues().apply {
                    put("id", program.id); put("sort", p); put("degree", program.degree); put("plan", program.plan)
                    put("name", program.name); put("degree_full", program.degreeFull); put("entry", program.entry)
                    put("credits", program.credits); put("fee_per_term", program.feePerTerm)
                    put("semesters", program.semesters); put("tone", program.tone.name)
                    put("revision", program.revision); put("placeholder", if (program.placeholder) 1 else 0)
                })
                program.structure.forEachIndexed { i, (name, credits) ->
                    db.insert(T_STRUCTURE, null, ContentValues().apply {
                        put("program_id", program.id); put("sort", i); put("name", name); put("credits", credits)
                    })
                }
                program.studyPlans.forEachIndexed { i, plan ->
                    db.insert(T_STUDY_PLAN, null, ContentValues().apply {
                        put("program_id", program.id); put("sort", i); put("name", plan.name); put("detail", plan.detail)
                    })
                }
                program.learn?.let { learn ->
                    db.insert(T_LEARN, null, ContentValues().apply {
                        put("program_id", program.id); put("intro", learn.intro); put("groups_title", learn.groupsTitle)
                    })
                    learn.groups.forEachIndexed { i, group ->
                        db.insert(T_LEARN_GROUP, null, ContentValues().apply {
                            put("program_id", program.id); put("sort", i); put("name_th", group.nameTh)
                            put("name_en", group.nameEn); put("courses", group.courses)
                        })
                    }
                    learn.outcomes.forEachIndexed { i, text ->
                        db.insert(T_LEARN_OUTCOME, null, ContentValues().apply {
                            put("program_id", program.id); put("sort", i); put("text", text)
                        })
                    }
                }
                program.careers.forEachIndexed { i, career ->
                    db.insert(T_CAREER, null, ContentValues().apply {
                        put("program_id", program.id); put("sort", i); put("name", career.name); put("icon", career.icon)
                    })
                }
                program.terms.forEachIndexed { t, term ->
                    val termId = db.insert(T_TERM, null, ContentValues().apply {
                        put("program_id", program.id); put("sort", t); put("title", term.title)
                    })
                    term.courses.forEachIndexed { c, course ->
                        db.insert(T_COURSE, null, ContentValues().apply {
                            put("term_id", termId); put("sort", c); put("code", course.code)
                            put("name_th", course.nameTh); put("name_en", course.nameEn); put("credits", course.credits)
                            put("hours", course.hours); put("english", if (course.english) 1 else 0)
                            put("prerequisite", course.prerequisite)
                            put("description_en", course.descriptionEn); put("description_th", course.descriptionTh)
                        })
                    }
                }
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    companion object {
        const val DB_NAME = "fitm.db"
        const val DB_VERSION = 4

        const val T_PROGRAM = "program"
        const val T_STRUCTURE = "structure"
        const val T_STUDY_PLAN = "study_plan"
        const val T_TERM = "term"
        const val T_COURSE = "course"
        const val T_LEARN = "learn"
        const val T_LEARN_GROUP = "learn_group"
        const val T_LEARN_OUTCOME = "learn_outcome"
        const val T_CAREER = "career"
    }
}

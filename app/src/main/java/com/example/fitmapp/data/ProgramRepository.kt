package com.example.fitmapp.data

/**
 * ทุกหน้าขอข้อมูลหลักสูตรผ่านตัวนี้ตัวเดียว
 *
 * ตอนนี้: ข้อมูล hardcode ใน ProgramData
 * ทีหลัง (SQLite): สร้าง DatabaseHelper : SQLiteOpenHelper ที่ insert ProgramData เป็นข้อมูลเริ่มต้น
 * แล้วแก้แค่ฟังก์ชันในไฟล์นี้ให้ query จากฐานข้อมูล — Activity / Adapter ไม่ต้องแก้
 */
object ProgramRepository {

    fun programs(): List<Program> = ProgramData.programs

    /** degree = "วท.บ." / "วศ.บ." / "อส.บ." หรือ null = ทั้งหมด */
    fun programs(degree: String?): List<Program> =
        if (degree == null) programs() else programs().filter { it.degree == degree }

    fun program(id: String?): Program = ProgramData.find(id)

    fun terms(programId: String?): List<Term> = program(programId).terms

    fun courseName(programId: String?, code: String): String? =
        ProgramData.courseName(program(programId), code)
}

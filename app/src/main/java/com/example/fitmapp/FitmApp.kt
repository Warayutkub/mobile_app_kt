package com.example.fitmapp

import android.app.Application
import com.example.fitmapp.data.ProgramRepository

/** ทำงานก่อนทุกหน้า: เตรียมฐานข้อมูล SQLite ให้ ProgramRepository (ลงทะเบียนใน AndroidManifest android:name) */
class FitmApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ProgramRepository.init(this)
    }
}

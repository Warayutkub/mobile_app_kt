package com.example.fitmapp

import android.app.Application
import com.example.fitmapp.data.ProgramRepository

class FitmApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ProgramRepository.init(this)
    }
}

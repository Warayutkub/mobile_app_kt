package com.example.fitmapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.ui.keepClearOfSystemBars
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Learn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)
        keepClearOfSystemBars()
        val gotoCareers = findViewById<CardView>(R.id.gotoCareersBtn)
        gotoCareers.setOnClickListener {
            intent = Intent(this, Careers::class.java)
            startActivity(intent)
        }
    }
}
package com.example.fitmapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitmapp.data.Career
import com.example.fitmapp.data.ProgramRepository
import com.example.fitmapp.ui.Screens

class Careers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_careers)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }
        findViewById<View>(R.id.btnViewAllCourses).setOnClickListener {
            startActivity(Intent(this, ProgramsActivity::class.java))
        }

        val programId = intent.getStringExtra(Screens.EXTRA_PROGRAM_ID)
        if (programId != null) showCareers(ProgramRepository.program(programId).careers)
    }

    private fun showCareers(careers: List<Career>) {
        if (careers.isEmpty()) return
        val grid = findViewById<GridLayout>(R.id.gridCareers)
        grid.removeAllViews()
        for (career in careers) {
            val card = layoutInflater.inflate(R.layout.item_career, grid, false)
            card.findViewById<ImageView>(R.id.careerIcon).setImageResource(resources.getIdentifier(career.icon, "drawable", packageName))
            card.findViewById<TextView>(R.id.careerName).text = career.name
            grid.addView(card)
        }
    }
}
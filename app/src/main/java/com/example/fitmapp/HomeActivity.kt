package com.example.fitmapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.data.Program
import com.example.fitmapp.data.ProgramRepository
import com.example.fitmapp.ui.BottomNav
import com.example.fitmapp.ui.Screens
import com.example.fitmapp.ui.Tab
import com.example.fitmapp.ui.applySystemBars
import com.example.fitmapp.ui.tintBackground

class HomeActivity : AppCompatActivity() {

    private val features = listOf(
        "bsc_it_4y" to "4 ปี · เทียบโอน",
        "beng_ine_4y" to "4 ปี · เทียบโอน 3 ปี",
        "bind_it_2y" to "ต่อเนื่อง 2 ปี"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        applySystemBars(findViewById(R.id.main), findViewById(R.id.header), lightStatusBar = false)
        BottomNav.setup(this, Tab.HOME)

        findViewById<View>(R.id.seeAllPrograms).setOnClickListener {
            startActivity(Intent(this, ProgramsActivity::class.java))
        }
        showFeatures()
        setupTiles()
    }

    private fun showFeatures() {
        val list = findViewById<LinearLayout>(R.id.featureList)
        val inflater = LayoutInflater.from(this)
        for ((id, meta) in features) {
            val program = ProgramRepository.program(id)
            val card = inflater.inflate(R.layout.item_program_feature, list, false)
            bindFeature(card, program, meta)
            card.setOnClickListener { openDetail(program.id) }
            list.addView(card)
        }
    }

    private fun bindFeature(card: View, program: Program, meta: String) {
        card.findViewById<FrameLayout>(R.id.featureImage).setBackgroundResource(program.tone.tint)
        card.findViewById<ImageView>(R.id.featureImageIcon).setColorFilter(getColor(program.tone.color))
        card.findViewById<TextView>(R.id.featureDegree).apply {
            text = program.degree
            setTextColor(getColor(program.tone.color))
            tintBackground(program.tone.tint)
        }
        card.findViewById<TextView>(R.id.featureName).text = program.name
        card.findViewById<TextView>(R.id.featureMeta).text = meta
    }

    private fun setupTiles() {
        tile(R.id.tileAbout, R.drawable.ic_info, "เกี่ยวกับภาควิชา") { Screens.open(this, Screens.ABOUT) }
        tile(R.id.tileQualification, R.drawable.ic_cap, "คุณวุฒิผู้สมัคร") { Screens.open(this, Screens.QUALIFICATION) }
        tile(R.id.tileFees, R.drawable.ic_wallet, "ค่าใช้จ่าย") { Screens.open(this, Screens.FEES) }
        tile(R.id.tileLearn, R.drawable.ic_spark, "เรียนได้อะไร") { Screens.open(this, Screens.LEARN) }
        tile(R.id.tileCareers, R.drawable.ic_ui_briefcase, "จบแล้วทำอะไร") { Screens.open(this, Screens.CAREERS) }
        tile(R.id.tileGallery, R.drawable.ic_image, "ภาพกิจกรรม") { Screens.open(this, Screens.GALLERY) }
    }

    private fun tile(id: Int, icon: Int, label: String, onClick: () -> Unit) {
        val tile = findViewById<View>(id)
        tile.findViewById<ImageView>(R.id.tileIcon).setImageResource(icon)
        tile.findViewById<TextView>(R.id.tileLabel).text = label
        tile.setOnClickListener { onClick() }
    }

    private fun openDetail(programId: String) {
        startActivity(Intent(this, ProgramDetailActivity::class.java).putExtra(Screens.EXTRA_PROGRAM_ID, programId))
    }
}

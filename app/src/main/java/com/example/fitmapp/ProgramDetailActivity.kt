package com.example.fitmapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.data.Program
import com.example.fitmapp.data.ProgramData
import com.example.fitmapp.data.ProgramRepository
import com.example.fitmapp.ui.Screens
import com.example.fitmapp.ui.applySystemBars
import com.example.fitmapp.ui.tintBackground
import com.example.fitmapp.ui.withComma

/** 04 Program Detail: ข้อมูลหลักสูตรที่เลือก (รับ id ผ่าน Screens.EXTRA_PROGRAM_ID) */
class ProgramDetailActivity : AppCompatActivity() {

    private lateinit var program: Program

    /** สีแท่งโครงสร้างหลักสูตร เรียงตามหมวด (Figma: ฟ้า / น้ำเงิน / เหลือง) */
    private val structureColors = listOf(R.color.accent, R.color.primary, R.color.highlight)
    private val planIcons = listOf(R.drawable.ic_code, R.drawable.ic_briefcase)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_program_detail)
        applySystemBars(findViewById(R.id.main), findViewById(R.id.appBar), lightStatusBar = true)

        program = ProgramRepository.program(intent.getStringExtra(Screens.EXTRA_PROGRAM_ID))

        findViewById<TextView>(R.id.appBarTitle).text = "รายละเอียดหลักสูตร"
        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }
        findViewById<View>(R.id.btnApply).setOnClickListener { Screens.openUrl(this, ProgramData.APPLY_URL) }

        showHeading()
        showFacts()
        showStructure()
        showPlans()
        showLinks()
    }

    private fun showHeading() {
        findViewById<View>(R.id.detailHero).tintBackground(program.tone.tint)
        findViewById<ImageView>(R.id.detailHeroIcon).setColorFilter(getColor(program.tone.color))
        findViewById<TextView>(R.id.detailHeroLabel).setTextColor(getColor(program.tone.color))

        findViewById<TextView>(R.id.detailDegree).apply {
            text = program.degree
            setTextColor(getColor(program.tone.color))
            tintBackground(program.tone.tint)
        }
        findViewById<TextView>(R.id.detailPlan).text = program.plan
        findViewById<TextView>(R.id.detailRevision).apply {
            text = program.revision
            visibility = if (program.revision.isEmpty()) View.GONE else View.VISIBLE
        }
        findViewById<TextView>(R.id.detailName).text = program.name
        findViewById<TextView>(R.id.detailDegreeFull).text = program.degreeFull
        findViewById<View>(R.id.detailPlaceholderNote).visibility = if (program.placeholder) View.VISIBLE else View.GONE
    }

    private fun showFacts() {
        fact(R.id.factCredits, program.credits.toString(), "หน่วยกิต")
        fact(R.id.factSemesters, program.semesters.toString(), "ภาคเรียน")
        fact(R.id.factFee, "≈${program.feePerTerm.withComma()}", "บาท / ภาค")
    }

    private fun fact(id: Int, value: String, label: String) {
        val box = findViewById<View>(id)
        box.findViewById<TextView>(R.id.factValue).text = value
        box.findViewById<TextView>(R.id.factLabel).text = label
    }

    /** แท่งสัดส่วน: ใช้ layout_weight = หน่วยกิตของแต่ละหมวด */
    private fun showStructure() {
        val card = findViewById<View>(R.id.structureCard)
        if (program.structure.isEmpty()) {
            card.visibility = View.GONE
            return
        }
        val bar = findViewById<LinearLayout>(R.id.structureBar)
        val legend = findViewById<LinearLayout>(R.id.structureLegend)
        val inflater = LayoutInflater.from(this)
        val gap = resources.getDimensionPixelSize(R.dimen.space_xs) / 2

        program.structure.forEachIndexed { i, (name, credits) ->
            val color = structureColors[i % structureColors.size]

            val segment = View(this)
            segment.setBackgroundColor(getColor(color))
            val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, credits.toFloat())
            if (i > 0) params.marginStart = gap
            bar.addView(segment, params)

            val row = inflater.inflate(R.layout.item_legend, legend, false)
            row.findViewById<View>(R.id.legendDot).tintBackground(color)
            row.findViewById<TextView>(R.id.legendName).text = name
            row.findViewById<TextView>(R.id.legendValue).text = "$credits หน่วยกิต"
            legend.addView(row)
        }
    }

    private fun showPlans() {
        val section = findViewById<View>(R.id.plansSection)
        if (program.studyPlans.isEmpty()) {
            section.visibility = View.GONE
            return
        }
        val list = findViewById<LinearLayout>(R.id.planList)
        val inflater = LayoutInflater.from(this)
        program.studyPlans.forEachIndexed { i, plan ->
            val card = inflater.inflate(R.layout.item_plan, list, false)
            card.findViewById<ImageView>(R.id.planIcon).setImageResource(planIcons[i % planIcons.size])
            card.findViewById<TextView>(R.id.planName).text = plan.name
            card.findViewById<TextView>(R.id.planDetail).text = plan.detail
            list.addView(card)
        }
    }

    private fun showLinks() {
        val list = findViewById<LinearLayout>(R.id.linkList)
        link(list, R.drawable.ic_book, "รายวิชา", "วิชาที่เรียนแยกตามปีและภาคเรียน") {
            startActivity(Intent(this, SubjectsActivity::class.java).putExtra(Screens.EXTRA_PROGRAM_ID, program.id))
        }
        link(list, R.drawable.ic_cap, "คุณวุฒิผู้สมัคร", "ใครสมัครหลักสูตรนี้ได้บ้าง") {
            Screens.open(this, Screens.QUALIFICATION, program.id)
        }
        link(list, R.drawable.ic_wallet, "ค่าใช้จ่าย", "ค่าเทอมและยอดรวมตลอดหลักสูตร") {
            Screens.open(this, Screens.FEES, program.id)
        }
        link(list, R.drawable.ic_spark, "เรียนได้อะไร", "กลุ่มวิชาเฉพาะทางและทักษะที่ได้") {
            Screens.open(this, Screens.LEARN, program.id)
        }
    }

    private fun link(parent: LinearLayout, icon: Int, title: String, subtitle: String, onClick: () -> Unit) {
        val row = LayoutInflater.from(this).inflate(R.layout.item_link_row, parent, false)
        row.findViewById<ImageView>(R.id.linkIcon).setImageResource(icon)
        row.findViewById<TextView>(R.id.linkTitle).text = title
        row.findViewById<TextView>(R.id.linkSubtitle).text = subtitle
        row.setOnClickListener { onClick() }
        parent.addView(row)
    }
}

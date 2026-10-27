package com.example.fitmapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.data.ProgramRepository
import com.example.fitmapp.ui.BottomNav
import com.example.fitmapp.ui.Screens
import com.example.fitmapp.ui.Tab
import com.example.fitmapp.ui.applySystemBars

/** 03 Programs: รายการหลักสูตรทั้งหมด กรองตามประเภทปริญญาได้ */
class ProgramsActivity : AppCompatActivity() {

    private lateinit var adapter: ProgramAdapter

    /** ชิป → ประเภทปริญญา (null = ทั้งหมด) */
    private val chips = listOf(
        R.id.chipAll to null,
        R.id.chipIT to "วท.บ.",
        R.id.chipINE to "วศ.บ.",
        R.id.chipIND to "อส.บ."
    )
    private var selectedChip = R.id.chipAll

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_programs)
        applySystemBars(findViewById(R.id.main), findViewById(R.id.appBar), lightStatusBar = true)
        BottomNav.setup(this, Tab.PROGRAMS)

        findViewById<TextView>(R.id.appBarTitle).text = "หลักสูตร"
        // หน้าแท็บหลัก ไม่มีปุ่มกลับ: ชื่อหน้าชิดแนวเดียวกับเนื้อหา
        findViewById<View>(R.id.btnBack).visibility = View.GONE
        findViewById<View>(R.id.appBar).apply {
            setPaddingRelative(resources.getDimensionPixelSize(R.dimen.space_lg), paddingTop, paddingEnd, paddingBottom)
        }

        adapter = ProgramAdapter(this, ProgramRepository.programs().toMutableList())
        val grid = findViewById<GridView>(R.id.programList)
        grid.adapter = adapter
        grid.setOnItemClickListener { _, _, position, _ ->
            val program = adapter.getItem(position) ?: return@setOnItemClickListener
            startActivity(
                Intent(this, ProgramDetailActivity::class.java)
                    .putExtra(Screens.EXTRA_PROGRAM_ID, program.id)
            )
        }

        for ((chipId, _) in chips) findViewById<TextView>(chipId).setOnClickListener { selectChip(chipId) }
        // หมุนจอแล้วยังกรองแบบเดิม
        selectChip(savedInstanceState?.getInt(KEY_CHIP) ?: R.id.chipAll)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_CHIP, selectedChip)
    }

    private fun selectChip(chipId: Int) {
        selectedChip = chipId
        for ((id, _) in chips) {
            val chip = findViewById<TextView>(id)
            val on = id == chipId
            chip.setBackgroundResource(if (on) R.drawable.bg_chip_selected else R.drawable.bg_chip_default)
            chip.setTextColor(getColor(if (on) R.color.white else R.color.text))
        }
        val degree = chips.first { it.first == chipId }.second
        adapter.clear()
        adapter.addAll(ProgramRepository.programs(degree))
        adapter.notifyDataSetChanged()
    }

    private companion object {
        const val KEY_CHIP = "selected_chip"
    }
}

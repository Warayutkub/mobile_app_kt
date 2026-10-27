package com.example.fitmapp


import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.data.ProgramRepository
import com.example.fitmapp.ui.Screens
import com.example.fitmapp.ui.keepClearOfSystemBars
import com.example.fitmapp.ui.withComma
import com.google.android.material.chip.ChipGroup



class N_08_Fees : AppCompatActivity() {

    private lateinit var tvFeePerSemester: TextView
    private lateinit var tvSemesterCount: TextView
    private lateinit var tvTotalFee: TextView
    private lateinit var chipGroupPrograms: ChipGroup

    private val chipProgram = mapOf(
        R.id.chipIt4 to "bsc_it_4y",
        R.id.chipItTransfer to "bsc_it_transfer",
        R.id.chipIne4 to "beng_ine_4y",
        R.id.chipIneTransfer to "beng_ine_transfer",
        R.id.chipItContinue to "bind_it_2y"
    )

    // Map เก็บข้อมูลค่าเทอมของแต่ละ Chip ID
    private val programFeeMap: Map<Int, ProgramFeeInfo> by lazy {
        chipProgram.mapValues { (_, id) ->
            val p = ProgramRepository.program(id)
            ProgramFeeInfo("${p.feePerTerm.withComma()} ฿", "${p.semesters} ภาค", "≈ ${(p.feePerTerm * p.semesters).withComma()} ฿")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_n08_fees)
        keepClearOfSystemBars()
        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }

        tvFeePerSemester = findViewById(R.id.tvFeePerSemester)
        tvSemesterCount = findViewById(R.id.tvSemesterCount)
        tvTotalFee = findViewById(R.id.tvTotalFee)
        chipGroupPrograms = findViewById(R.id.chipGroupPrograms)

        setupTableRows()

        // Event เมื่อเปลี่ยนการเลือก Chip หลักสูตรด้านบน
        chipGroupPrograms.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val selectedId = checkedIds[0]
                updateFeeInfo(selectedId)
            }
        }
        if (savedInstanceState == null) selectProgram(intent.getStringExtra(Screens.EXTRA_PROGRAM_ID))
        updateFeeInfo(chipGroupPrograms.checkedChipId)
    }

    private fun selectProgram(programId: String?) {
        val chipId = chipProgram.entries.firstOrNull { it.value == programId }?.key ?: return
        chipGroupPrograms.check(chipId)
        chipGroupPrograms.post {
            val chip = findViewById<View>(chipId)
            (chipGroupPrograms.parent as? HorizontalScrollView)?.smoothScrollTo(chip.left, 0)
        }
    }

    private fun updateFeeInfo(chipId: Int) {
        val info = programFeeMap[chipId] ?: return
        tvFeePerSemester.text = info.feePerSemester
        tvSemesterCount.text = info.semesterCount
        tvTotalFee.text = info.totalFee
    }

    private fun setupTableRows() {
        setupRow(findViewById(R.id.rowIt4), "วท.บ. IT 4 ปี", "bsc_it_4y")
        setupRow(findViewById(R.id.rowItTransfer), "วท.บ. IT เทียบโอน", "bsc_it_transfer")
        setupRow(findViewById(R.id.rowIne4), "วศ.บ. INE 4 ปี", "beng_ine_4y")
        setupRow(findViewById(R.id.rowIneTransfer), "วศ.บ. INE เทียบโอน", "beng_ine_transfer")
        setupRow(findViewById(R.id.rowItContinue), "อส.บ. IT ต่อเนื่อง", "bind_it_2y")
    }

    private fun setupRow(rowView: View, title: String, programId: String) {
        val p = ProgramRepository.program(programId)
        setupRow(rowView, title, p.feePerTerm.withComma(), (p.feePerTerm * p.semesters).withComma())
    }

    private fun setupRow(rowView: View, title: String, perSemester: String, total: String) {
        rowView.findViewById<TextView>(R.id.tvRowTitle).text = title
        rowView.findViewById<TextView>(R.id.tvRowPerSemester).text = perSemester
        rowView.findViewById<TextView>(R.id.tvRowTotal).text = total
    }
}
package com.example.fitmapp


import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitmapp.ui.keepClearOfSystemBars
import com.google.android.material.chip.ChipGroup



class N_08_Fees : AppCompatActivity() {

    private lateinit var tvFeePerSemester: TextView
    private lateinit var tvSemesterCount: TextView
    private lateinit var tvTotalFee: TextView
    private lateinit var chipGroupPrograms: ChipGroup

    // Map เก็บข้อมูลค่าเทอมของแต่ละ Chip ID
    private val programFeeMap = mapOf(
        R.id.chipIt4 to ProgramFeeInfo("19,000 ฿", "8 ภาค", "≈ 152,000 ฿"),
        R.id.chipItTransfer to ProgramFeeInfo("19,000 ฿", "5 ภาค", "≈ 95,000 ฿"),
        R.id.chipIne4 to ProgramFeeInfo("25,000 ฿", "8 ภาค", "≈ 200,000 ฿"),
        R.id.chipIneTransfer to ProgramFeeInfo("25,000 ฿", "6 ภาค", "≈ 150,000 ฿"),
        R.id.chipItContinue to ProgramFeeInfo("19,000 ฿", "4 ภาค", "≈ 76,000 ฿")
    )

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
    }

    private fun updateFeeInfo(chipId: Int) {
        val info = programFeeMap[chipId] ?: return
        tvFeePerSemester.text = info.feePerSemester
        tvSemesterCount.text = info.semesterCount
        tvTotalFee.text = info.totalFee
    }

    private fun setupTableRows() {
        setupRow(findViewById(R.id.rowIt4), "วท.บ. IT 4 ปี", "19,000", "152,000")
        setupRow(findViewById(R.id.rowItTransfer), "วท.บ. IT เทียบโอน", "19,000", "95,000")
        setupRow(findViewById(R.id.rowIne4), "วศ.บ. INE 4 ปี", "25,000", "200,000")
        setupRow(findViewById(R.id.rowIneTransfer), "วศ.บ. INE เทียบโอน", "25,000", "150,000")
        setupRow(findViewById(R.id.rowItContinue), "อส.บ. IT ต่อเนื่อง", "19,000", "76,000")
    }

    private fun setupRow(rowView: View, title: String, perSemester: String, total: String) {
        rowView.findViewById<TextView>(R.id.tvRowTitle).text = title
        rowView.findViewById<TextView>(R.id.tvRowPerSemester).text = perSemester
        rowView.findViewById<TextView>(R.id.tvRowTotal).text = total
    }
}
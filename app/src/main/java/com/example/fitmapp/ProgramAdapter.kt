package com.example.fitmapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.fitmapp.data.Program
import com.example.fitmapp.ui.tintBackground
import com.example.fitmapp.ui.withComma

/** การ์ดหลักสูตรใน GridView หน้า 03 (layout/item_program.xml) */
class ProgramAdapter(context: Context, programs: MutableList<Program>) :
    ArrayAdapter<Program>(context, R.layout.item_program, programs) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_program, parent, false)
        val program = getItem(position)!!

        view.findViewById<TextView>(R.id.programDegree).apply {
            text = program.degree
            setTextColor(context.getColor(program.tone.color))
            tintBackground(program.tone.tint)
        }
        view.findViewById<TextView>(R.id.programPlan).text = program.plan
        view.findViewById<TextView>(R.id.programName).text = program.name
        view.findViewById<TextView>(R.id.programEntry).text = program.entry
        view.findViewById<TextView>(R.id.programCredits).text = program.credits.toString()
        view.findViewById<TextView>(R.id.programFee).text = "≈ ${program.feePerTerm.withComma()} ฿"
        return view
    }
}

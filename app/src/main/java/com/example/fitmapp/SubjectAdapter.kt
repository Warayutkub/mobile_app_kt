package com.example.fitmapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.fitmapp.data.Course

class SubjectAdapter(context: Context, courses: MutableList<Course>) :
    ArrayAdapter<Course>(context, R.layout.item_subject, courses) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false)
        val course = getItem(position)!!

        view.findViewById<TextView>(R.id.subjectCode).text = course.code
        view.findViewById<View>(R.id.subjectEnBadge).visibility = if (course.english) View.VISIBLE else View.GONE
        view.findViewById<TextView>(R.id.subjectName).text = course.nameTh
        view.findViewById<TextView>(R.id.subjectNameEn).text = course.nameEn
        view.findViewById<TextView>(R.id.subjectCredits).text = course.credits.toString()
        return view
    }
}

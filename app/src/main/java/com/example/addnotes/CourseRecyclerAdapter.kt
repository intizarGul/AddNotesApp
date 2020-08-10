package com.example.addnotes

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_course_list.view.*
import java.util.ArrayList

class CourseRecyclerAdapter(context: Context, private var coursesList: List<CourseInfo>) :
        RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_course_list, parent, false)
        return ViewHolder(itemView)
    }
    override fun getItemCount() = coursesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = coursesList[position]
        holder.setData(course.title,position)
    }
    
    fun setCourses(courses: ArrayList<CourseInfo>) {
        coursesList = courses
        //call the function to track the changes
        notifyDataSetChanged()
    }
    
    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val textCourse = itemView?.findViewById<TextView?>(R.id.courseDetails)
        var coursePosition = 0
        
        fun setData(CourseText:String,pos:Int) {
            //item view values
            itemView.courseDetails.text = CourseText
            
            //the var position is = the pos passed in parameter
            this.coursePosition = pos
        }
        
        init {
            itemView?.setOnClickListener {
                //Snackbar.make(it, coursesList[coursePosition]?.title, Snackbar.LENGTH_LONG).show()
                Snackbar.make(it,NotesCollectionDBContract.CourseInfoEntry.COLUMN_TITLE,Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
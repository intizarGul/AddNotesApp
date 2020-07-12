@file:Suppress("DEPRECATION")

package com.example.addnotes.ui.courses

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.addnotes.CourseRecyclerAdapter
import com.example.addnotes.DataManager
import com.example.addnotes.NoteRecyclerAdapter
import com.example.addnotes.R
import com.example.addnotes.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.fragment_courses.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_course_list.*

@Suppress("DEPRECATION")
class CoursesFragment : Fragment(R.layout.fragment_courses) {
	
	companion object {
		fun newInstance () = CoursesFragment()
	}
	private var courseListState: Parcelable? = null
	private val courseLayoutManager by lazy {
		GridLayoutManager(context,2)
	}
	private val courseRecyclerAdapter by lazy {
		context?.let { CourseRecyclerAdapter(it,DataManager.courses.values.toList()) }
	}
	
	private lateinit var coursesViewModel: CoursesViewModel
	
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		activity.let { coursesViewModel = ViewModelProviders.of(it!!).get(CoursesViewModel::class.java) }
		listCourses?.layoutManager = courseLayoutManager
		listCourses?.adapter = courseRecyclerAdapter
	}
	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		courseListState = courseLayoutManager.onSaveInstanceState()
		outState.putParcelable("LIST_STATE_KEY", courseListState)
	}
	
	override fun onViewStateRestored(savedInstanceState: Bundle?) {
		super.onViewStateRestored(savedInstanceState)
		if (savedInstanceState != null)
			courseListState = savedInstanceState.getParcelable("LIST_STATE_KEY")
	}
	
	override fun onResume() {
		super.onResume()
		if (courseListState != null)
			courseLayoutManager.onRestoreInstanceState(courseListState)
	}
}
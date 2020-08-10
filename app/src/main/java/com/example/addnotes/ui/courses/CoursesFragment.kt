@file:Suppress("DEPRECATION")

package com.example.addnotes.ui.courses

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.addnotes.CourseRecyclerAdapter
import com.example.addnotes.DataManager
import com.example.addnotes.NotesCollectionDatabaseHelper
import com.example.addnotes.R
import kotlinx.android.synthetic.main.fragment_courses.*

@Suppress("DEPRECATION")
class CoursesFragment : Fragment(R.layout.fragment_courses) {
	lateinit var databaseHelper: NotesCollectionDatabaseHelper
	
	/*companion object {
		//fun newInstance () = CoursesFragment()
	}*/
	
	private var courseListState: Parcelable? = null
	
	private val courseLayoutManager by lazy {
		//Create an instance of the GridLayoutManger
		//get the layout grid from resources values this will adapt to different displays
		GridLayoutManager(context,resources.getInteger(R.integer.course_grid_span))
	}
	
	private val courseRecyclerAdapter by lazy {
		context?.let { CourseRecyclerAdapter(it,DataManager.fetchAllCourses(databaseHelper!!).toList()) }
	}
	
	private lateinit var coursesViewModel: CoursesViewModel
	
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		
		databaseHelper = NotesCollectionDatabaseHelper(activity)
		
		activity.let { coursesViewModel = ViewModelProviders.of(it!!).get(CoursesViewModel::class.java) }
		listCourses?.layoutManager = courseLayoutManager
		//listCourses?.adapter = courseRecyclerAdapter
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
@file:Suppress("DEPRECATION")

package com.example.addnotes.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.addnotes.DataManager
import com.example.addnotes.NoteRecyclerAdapter
import com.example.addnotes.R
import com.example.addnotes.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_notes.*

@Suppress("DEPRECATION")
class NotesFragment : Fragment() {
	companion object {
		fun newInstance() = NotesFragment()
	}
	private lateinit var notesViewModel: NotesViewModel
	private val homeLayoutManager by lazy {
		LinearLayoutManager(context)
	}
	private val noteRecyclerAdapter by lazy {
		NoteRecyclerAdapter(requireContext(),DataManager.notes)
	}
	
	
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_notes, container, false)
	}
	
	
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		activity.let { notesViewModel = ViewModelProviders.of(it!!).get(NotesViewModel::class.java) }
		listNotes.layoutManager = homeLayoutManager
		listNotes?.adapter = noteRecyclerAdapter
	}
}
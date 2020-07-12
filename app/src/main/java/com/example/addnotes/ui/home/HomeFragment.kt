@file:Suppress("DEPRECATION")

package com.example.addnotes.ui.home

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.addnotes.DataManager
import com.example.addnotes.NoteRecyclerAdapter
import com.example.addnotes.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
	
	/*companion object {
		//fun newInstance() = HomeFragment()
	}*/
	private var itemsListState: Parcelable? = null
	
	private val noteRecyclerAdapter by lazy {
		NoteRecyclerAdapter(requireContext(),DataManager.notes)
	}
	
	private lateinit var viewModel: HomeViewModel
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		
		return inflater.inflate(R.layout.fragment_home, container, false)
	}
	
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		activity.let { viewModel = ViewModelProviders.of(it!!).get(HomeViewModel::class.java) }
		
		//creating a new instance of the LinearLayout manager locally
		listItems?.layoutManager = LinearLayoutManager(context)
		//listItems?.layoutManager = homeLayoutManager
		listItems?.adapter = noteRecyclerAdapter
	}
	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		itemsListState = listItems?.layoutManager?.onSaveInstanceState()
		outState?.putParcelable("LIST_STATE_KEY", itemsListState)
	}
	override fun onViewStateRestored(savedInstanceState: Bundle?) {
		super.onViewStateRestored(savedInstanceState)
		if (savedInstanceState != null)
			itemsListState = savedInstanceState.getParcelable("LIST_STATE_KEY")
	}
	override fun onResume() {
		super.onResume()
		if (itemsListState != null)
			listItems.layoutManager?.onRestoreInstanceState(itemsListState)
	}
}

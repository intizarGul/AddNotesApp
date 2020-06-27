@file:Suppress("DEPRECATION")

package com.example.addnotes.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.addnotes.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
	companion object {
		fun newInstance() = HomeFragment()
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
		listItems.layoutManager = LinearLayoutManager(context)
		listItems?.adapter = context?.let { NoteRecyclerAdapter(it,DataManager.notes) }
	}

}
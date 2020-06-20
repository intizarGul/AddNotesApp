package com.example.addnotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.recyclerview.widget.RecyclerView

class NoteRecyclerAdapter(private val context: Context,private val notes: List<NoteInfo>):
		//NoteRecyclerAdapter Class extending the RecyclerView.Adapter class
		//RecyclerView.Adapter class expects a view so pass the NoteRecyclerAdapter.ViewHolder View class
		//RecyclerView.Adapter requires the 3 below methods to be implemented to view the individual items
	RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>(){
	//add a property to inflate layout
	private val layoutInflater = LayoutInflater.from(context)
	//inside the RecyclerAdapter Class we need a class to hold our view
	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		//populate the data fields to do so we need to access the fields from the recycler view
		val textCourse = itemView?.findViewById<TextView?>(R.id.textCourse)
		val textTitle = itemView?.findViewById<TextView?>(R.id.textTitle)
	
	
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		// we will this method to crete the view for displaying the individual items
		val itemView = layoutInflater.inflate(R.layout.item_note_list,parent,false)
		//to view item we will use the layoutInflater's property.inflate class
		//the first parameter is the reference to the layout which we created item_note_list
		// the 2nd parameter is the reference to view we pass the parent parameter of the onCreateViewHolder method
		// the last parameter is boolean value we set this to false means we don't the
		// view to be populated by the parents method this will be managed by the RecyclerView
		
		return ViewHolder(itemView)
	}
	
	override fun getItemCount()= notes.size
	
	
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		//display our notes from the NoteInfo Class
		val note = notes[position]
		holder.textCourse?.text = note.course?.title
		holder.textTitle?.text = note.title
	}
}
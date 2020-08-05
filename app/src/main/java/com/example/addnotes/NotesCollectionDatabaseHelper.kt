package com.example.addnotes

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotesCollectionDatabaseHelper(context:Context?):SQLiteOpenHelper(context,
	DATABASE_NAME,null,DATABASE_VERSION) {
	
	override fun onCreate(db: SQLiteDatabase?) {
		db?.execSQL(NotesCollectionDBContract.CourseInfoEntry.SQL_CREATE_TABLE)
		db?.execSQL(NotesCollectionDBContract.NoteInfoEntry.SQL_CREATE_TABLE)
		
		 val worker = DatabaseDataWorker(db)
		worker.insertCourses()
		worker.insertSampleNotes()
	}
	
	override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
		db?.execSQL(NotesCollectionDBContract.CourseInfoEntry.SQL_DROP_TABLE)
	}
	
	
	override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
		//implement later
		super.onDowngrade(db, oldVersion, newVersion)
	}
	
	override fun onOpen(db: SQLiteDatabase?) {
		//implement later
		super.onOpen(db)
	}
	companion object{
		const val DATABASE_NAME = "NoteKeeper.db"
		const val DATABASE_VERSION = 1
	}
}
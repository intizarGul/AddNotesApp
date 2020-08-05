package com.example.addnotes

import android.provider.BaseColumns

object NotesCollectionDBContract {
	
	//Create a course EntryObject
	object CourseInfoEntry :BaseColumns {
		const val TABLE_NAME = "course_info"
		const val COLUMN_ID:String = BaseColumns._ID
		const val COLUMN_COURSE_ID = "course_id"
		const val COLUMN_TITLE = "course_title"
		
		
		
		//Create the table
		const val SQL_CREATE_TABLE =
			"CREATE TABLE $TABLE_NAME (" +
					COLUMN_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"$COLUMN_COURSE_ID TEXT NOT NULL, " +
					"$COLUMN_TITLE TEXT NOT NULL)"
		
		
		
		
		
		// Test drop the table
		const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
	}
	
	object NoteInfoEntry:BaseColumns {
		const val TABLE_NAME = "note_info"
		const val COLUMN_ID:String = BaseColumns._ID
		const val COLUMN_TITLE = "note_title"
		const val COLUMN_TEXT = "note_text"
		const val COLUMN_COURSE_ID = "course_id"
		
		
		
		//Create the table
		const val SQL_CREATE_TABLE =
			"CREATE TABLE $TABLE_NAME (" +
					COLUMN_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"$COLUMN_TITLE TEXT NOT NULL, " +
					"$COLUMN_TEXT TEXT , " +
					"$COLUMN_COURSE_ID TEXT NOT NULL)"
	}
	
}
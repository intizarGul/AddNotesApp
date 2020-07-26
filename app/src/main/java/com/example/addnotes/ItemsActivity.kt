package com.example.addnotes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_notes.*

class ItemsActivity : AppCompatActivity() {
	private lateinit var appBarConfiguration: AppBarConfiguration
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_items)
		val toolbar: Toolbar = findViewById(R.id.toolbar)
		setSupportActionBar(toolbar)
		
		val fab: FloatingActionButton = findViewById(R.id.fab)
		fab.setOnClickListener{
			//open NoteActivity
			val intentActivity = Intent(this,NoteActivity::class.java)
			startActivity(intentActivity)
		}
		
		val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
		val navView: NavigationView = findViewById(R.id.nav_view)
		val navHostFragment =
			supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
		val navController: NavController = navHostFragment.navController
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		appBarConfiguration = AppBarConfiguration(setOf(
			
			R.id.nav_home, R.id.nav_notes, R.id.nav_courses), drawerLayout)
		setupActionBarWithNavController(navController, appBarConfiguration)
		navView.setupWithNavController(navController)
	}
	
	
	override fun onResume() {
		super.onResume()
		listItems?.adapter?.notifyDataSetChanged()
		listNotes?.adapter?.notifyDataSetChanged()
	}
	
	//Back Button Pressed close the drawer if open
	override fun onBackPressed() {
		if (drawer_layout.isDrawerOpen(GravityCompat.START)){
			drawer_layout.closeDrawer(GravityCompat.START)
		}else{
			super.onBackPressed()
		}
	}
	
	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.items, menu)
		return true
	}
	
	override fun onSupportNavigateUp(): Boolean {
		val navController = findNavController(R.id.nav_host_fragment)
		return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
	}
}
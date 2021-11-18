package org.wit.myassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_main.*
import org.wit.myassignment.R
import timber.log.Timber

class Home : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.i("home attempted access")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar2)
        Timber.i("home attempted render")

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar2, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        nav_menu.setNavigationItemSelectedListener (this)


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.home){
            Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
        }

        return true
    }
}
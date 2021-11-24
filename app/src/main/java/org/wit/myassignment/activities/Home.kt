package org.wit.myassignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_main.*
import org.wit.myassignment.R
import org.wit.myassignment.fragments.*
import org.wit.myassignment.fragments.Home
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
        drawerLayout.closeDrawer(GravityCompat.START)

        when(item.itemId) {
            R.id.home -> {
                setToolbarTitle("Home")
                changeFragment(Home())
            }
            R.id.workouts -> {
                setToolbarTitle("Workouts")
                //changeFragment(Workouts())
                val intent = Intent(this, TrainerListActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.bmi -> {
                setToolbarTitle("BMI")
                val intent = Intent(this, BMI::class.java)
                startActivity(intent)
                finish()
            }
            R.id.contact -> {
                setToolbarTitle("Contacts")
                changeFragment(Contact())
            }
            R.id.settings -> {
                setToolbarTitle("Settings")
                changeFragment(Settings())


            }
        }
        return true
    }

    fun setToolbarTitle(title:String){
        supportActionBar?.title = title
    }

    fun changeFragment(frag: Fragment){
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container, frag).commit()
    }
}
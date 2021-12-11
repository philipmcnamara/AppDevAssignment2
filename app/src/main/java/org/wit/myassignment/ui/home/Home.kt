package org.wit.myassignment.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.content_main.*
import org.wit.myassignment.R
import org.wit.myassignment.ui.bmi.BMI
import org.wit.myassignment.ui.weighttracking.WeightList
import org.wit.myassignment.ui.settings.SettingsActivity
import org.wit.myassignment.ui.workouts.TrainerListActivity
import org.wit.myassignment.ui.auth.LoggedInViewModel
import org.wit.myassignment.ui.auth.Login
import org.wit.myassignment.ui.contactinfo.Contact
import timber.log.Timber
import androidx.lifecycle.Observer


class Home : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var loggedInViewModel : LoggedInViewModel


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        setSupportActionBar(toolbar2)
        Timber.i("home attempted render")
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar2, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        nav_menu.setNavigationItemSelectedListener (this)

    }

    public override fun onStart() {
        super.onStart()
        loggedInViewModel = ViewModelProvider(this).get(LoggedInViewModel::class.java)
        loggedInViewModel.liveFirebaseUser.observe(this, Observer { firebaseUser ->
            if (firebaseUser != null) {
                loggedInViewModel.liveFirebaseUser.value!!
                Timber.i("onStart activated, first If hit")

            }
        })

        loggedInViewModel.loggedOut.observe(this, Observer { loggedout ->
            if (loggedout) {
                startActivity(Intent(this, Login::class.java))
                Timber.i("onStart activated, second If hit")
            }
        })

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)

        when(item.itemId) {
            R.id.home -> {
                setToolbarTitle("HomeFragment")
                changeFragment(HomeFragment())
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
            R.id.weightTracker -> {
                setToolbarTitle("Weight Tracker")
                var i = Intent(this,WeightList::class.java)
                startActivity(i)
                finish()
            }
            R.id.contact -> {
                setToolbarTitle("Contacts")
                changeFragment(Contact())
            }
            R.id.settings -> {
                setToolbarTitle("Settings")
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                finish()
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

    fun signOut(item: MenuItem) {
        loggedInViewModel.logOut()
        val intent = Intent(this, Login::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


}
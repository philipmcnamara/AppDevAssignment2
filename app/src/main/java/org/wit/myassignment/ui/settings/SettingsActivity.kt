package org.wit.myassignment.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.settings_activity.*
import org.wit.myassignment.R
import org.wit.myassignment.ui.auth.LoggedInViewModel
import org.wit.myassignment.ui.auth.Login
import timber.log.Timber
import androidx.lifecycle.Observer



class SettingsActivity : AppCompatActivity() {
    var checkedId = false


    private lateinit var loggedInViewModel : LoggedInViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
/*
        themeSwitch.setOnCheckedChangeListener { toggle, checkedId ->
            if (checkedId) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

 */

        darkMode.setOnClickListener{
            checkedId = true
            darkMode.isVisible = false
            if (checkedId) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        darkModeOff.setOnClickListener {
            checkedId = false
            if (checkedId == false) {
                darkModeOff.isVisible = false
                if (checkedId) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        btnLogOut.setOnClickListener{
            signOut()
        }
    }

    public override fun onStart() {
        super.onStart()
        var ninTent = false
        loggedInViewModel = ViewModelProvider(this).get(LoggedInViewModel::class.java)
        loggedInViewModel.liveFirebaseUser.observe(this, Observer { firebaseUser ->
            if (firebaseUser != null) {
                ninTent = true
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

    private fun signOut() {
        loggedInViewModel.logOut()
        val intent = Intent(this, Login::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        Timber.i("SIGNOUT!")
    }

    fun Home(view: android.view.View) {
        val intent = Intent(this, org.wit.myassignment.ui.home.Home::class.java)
        startActivity(intent)
        finish()
    }

}
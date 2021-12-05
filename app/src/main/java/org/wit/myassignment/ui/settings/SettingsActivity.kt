package org.wit.myassignment.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.settings_activity.*
import org.wit.myassignment.R
import org.wit.myassignment.ui.auth.LoggedInViewModel
import org.wit.myassignment.ui.auth.Login
import timber.log.Timber

class SettingsActivity : AppCompatActivity() {

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

        themeSwitch.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        btnLogOut.setOnClickListener{
            signOut()
        }
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
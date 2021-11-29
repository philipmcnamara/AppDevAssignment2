package org.wit.myassignment.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.settings_activity.*
import org.wit.myassignment.R

class SettingsActivity : AppCompatActivity() {

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
    }

    fun Home(view: android.view.View) {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }

}
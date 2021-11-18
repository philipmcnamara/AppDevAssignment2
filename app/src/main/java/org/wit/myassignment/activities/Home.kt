package org.wit.myassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_main.*
import org.wit.myassignment.R
import timber.log.Timber

class Home : AppCompatActivity() {
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

    }
}
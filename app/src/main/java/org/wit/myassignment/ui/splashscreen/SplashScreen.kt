package org.wit.myassignment.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import org.wit.myassignment.R
import org.wit.myassignment.ui.home.Home

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        //give the splash image full screen view
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // directs to the main page
        Handler().postDelayed({
            val intent = Intent(this, Home::class.java)
            //val intent = Intent(this, TrainerListActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
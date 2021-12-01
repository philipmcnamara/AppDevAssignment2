package org.wit.myassignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import org.wit.myassignment.R
import org.wit.myassignment.databinding.ActivityLoginBinding
import org.wit.myassignment.main.MainApp
import timber.log.Timber
import java.util.*


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityLoginBinding
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
       // auth = Firebase.auth
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)


        btnRegLogin.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }

        Loginbutton.setOnClickListener {
            app = application as MainApp
            var userList = app.users.findAll()

            userList.forEach {
                if ( loginEmail.text.toString().lowercase(Locale.getDefault()) == it.email.lowercase(Locale.getDefault()) && it.password == loginPassword.text.toString()) {
                val intent = Intent(this, SplashScreen::class.java)
                startActivity(intent)
                finish()
                Timber.i("Login Success: $it")
            }
            else {
                Timber.i("Login Failed Email Text : ${loginEmail.text}")
                Timber.i("Login Failed User Email: ${it.email}")
            } }
        }
        skipButton.setOnClickListener{
            val intent = Intent(this, SplashScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
}


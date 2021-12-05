package org.wit.myassignment.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login.*
import org.wit.myassignment.R
import org.wit.myassignment.ui.home.Home
import org.wit.myassignment.ui.splashscreen.SplashScreen
import org.wit.myassignment.databinding.ActivityLoginBinding
import org.wit.myassignment.main.MainApp
import timber.log.Timber
import androidx.lifecycle.Observer






class Login : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var loginBinding : ActivityLoginBinding
    private lateinit var loginRegisterViewModel : LoginRegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)


        btnRegLogin.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }

        Loginbutton.setOnClickListener {
            signIn(loginBinding.loginEmail.text.toString(),
                loginBinding.loginPassword.text.toString())
        }
        skipButton.setOnClickListener{
            val intent = Intent(this, SplashScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        loginRegisterViewModel = ViewModelProvider(this).get(LoginRegisterViewModel::class.java)
        loginRegisterViewModel.liveFirebaseUser.observe(this, Observer
        { firebaseUser -> if (firebaseUser != null)
            startActivity(Intent(this, Home::class.java)) })

        loginRegisterViewModel.firebaseAuthManager.errorStatus.observe(this, Observer
        { status -> checkStatus(status) })
    }


    //Required to exit app from Login Screen - must investigate this further
    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Click again to Close App...",Toast.LENGTH_LONG).show()
        finish()
    }

    private fun createAccount(email: String, password: String) {
        Timber.d("createAccount:$email")
        if (!validateForm()) { return }

        loginRegisterViewModel.register(email,password)
    }

    private fun signIn(email: String, password: String) {
        Timber.d("signIn:$email")
        if (!validateForm()) { return }

        loginRegisterViewModel.login(email,password)
    }

    private fun checkStatus(error:Boolean) {
        if (error)
            Toast.makeText(this,
                getString(R.string.auth_failed),
                Toast.LENGTH_LONG).show()
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = loginBinding.loginEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            loginBinding.loginEmail.error = "Required."
            valid = false
        } else {
            loginBinding.loginEmail.error = null
        }

        val password = loginBinding.loginPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            loginBinding.loginPassword.error = "Required."
            valid = false
        } else {
            loginBinding.loginPassword.error = null
        }
        return valid
    }
}



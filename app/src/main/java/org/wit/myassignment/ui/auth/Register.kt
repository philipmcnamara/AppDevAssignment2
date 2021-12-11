package org.wit.myassignment.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.wit.myassignment.R
import org.wit.myassignment.databinding.RegisterBinding
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.models.UserModel
import timber.log.Timber
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.wit.myassignment.ui.home.Home


class Register : AppCompatActivity() {
    private lateinit var registerBinding: RegisterBinding
    private lateinit var loginRegisterViewModel : LoginRegisterViewModel
    lateinit var app: MainApp
    var user = UserModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        registerBinding = RegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)


        app = application as MainApp

        registerBinding.btnLogRegister.setOnClickListener {
            onBackPressed()
        }

        registerBinding.RegisterButton.setOnClickListener() {

            createAccount(registerBinding.userEmail.text.toString(),
                registerBinding.userPassword.text.toString())
        }
        Timber.i("register Button Pressed: $user")
        setResult(RESULT_OK)
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

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
        Toast.makeText(this,"Click again to Close App...", Toast.LENGTH_LONG).show()
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

        val email = registerBinding.userEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            registerBinding.userEmail.error = "Required."
            valid = false
        } else {
            registerBinding.userEmail.error = null
        }

        val password = registerBinding.userPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            registerBinding.userPassword.error = "Required."
            valid = false
        } else {
            registerBinding.userPassword.error = null
        }
        return valid
    }


}

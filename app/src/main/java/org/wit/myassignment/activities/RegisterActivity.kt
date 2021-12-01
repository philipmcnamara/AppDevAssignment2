package org.wit.myassignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.wit.myassignment.R
import org.wit.myassignment.databinding.ActivityRegisterBinding
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.models.UserModel
import timber.log.Timber

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    lateinit var app: MainApp
    var user = UserModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this@RegisterActivity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        app = application as MainApp

        binding.btnLogRegister.setOnClickListener {
            onBackPressed()
        }

        binding.RegisterButton.setOnClickListener() {

            user.name = binding.userName.text.toString()
            user.email = binding.userEmail.text.toString()
            user.password = binding.userPassword.text.toString()



            if (user.name.isEmpty()) {
                Snackbar.make(it, R.string.enter_user_name, Snackbar.LENGTH_LONG).show()
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.name,user.password )
                    .addOnCompleteListener(

                          OnCompleteListener<AuthResult>  { task ->

                            //If the Registration is successful
                            if (task.isSuccessful){

                                //firebase registered user
                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                Timber.i("Firebase User: $firebaseUser")

                                Toast.makeText(
                                    this@RegisterActivity,
                                    "You are Registered Successfully",
                                    Toast.LENGTH_SHORT).show()

                                val intent = Intent(this@RegisterActivity, Home::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
                                intent.putExtra("email_id", user.email)
                                startActivity(intent)
                                finish()

                            }
                        }
                    )


                onBackPressed()
            }




/*
            if (user.name.isEmpty()) {
                Snackbar.make(it, R.string.enter_user_name, Snackbar.LENGTH_LONG).show()
            } else {
                app.users.createUser(user.copy())
                onBackPressed()
            }

 */


        }
        Timber.i("register Button Pressed: $user")
        setResult(RESULT_OK)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }
}

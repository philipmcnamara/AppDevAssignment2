package org.wit.myassignment.ui.weighttracking

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.myassignment.R
import org.wit.myassignment.databinding.ActivityPlansBinding
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.models.exerciseModel
import org.wit.myassignment.ui.home.Home
import timber.log.Timber
import timber.log.Timber.i

class RoutineActivity  : AppCompatActivity() {
    private lateinit var binding: ActivityPlansBinding
    var routine = exerciseModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var edit = false

        setContentView(R.layout.activity_plans_list)

        binding = ActivityPlansBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAddRoutine.title = title
        setSupportActionBar(binding.toolbarAddRoutine)
        app = application as MainApp

        binding.planRoutine.setText(routine.title)
        binding.planSet1.setText(routine.Set1)

        if (intent.hasExtra("routine_Edit")) {
            edit = true
            routine = intent.extras?.getParcelable("routine_Edit")!!
            binding.planRoutine.setText(routine.title)
            binding.planSet1.setText(routine.Set1)
            binding.btnAdd.setText(R.string.save_routine)
            binding.btnDeletePlan.setVisibility(View.VISIBLE)
            binding.btnDeletePlan.setOnClickListener() {
                app.routines.delete(routine)
                setResult(RESULT_OK)
                finish()
            }
        }

        binding.btnAdd.setOnClickListener() {
            routine.title = binding.planRoutine.text.toString()
            routine.Set1 = binding.planSet1.text.toString()
            if (routine.title.isEmpty()) {
                i("add Button Pressed: ${routine}")
                Snackbar.make(it,R.string.enter_routine_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.routines.update(routine.copy())
                    i("add Button Pressed: ${routine}")
                } else {
                    app.routines.create(routine.copy())
                    i("add Button Pressed: ${routine}")
                }
            }
            setResult(RESULT_OK)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
            R.id.item_home -> {

                Timber.i("HomeFragment Button Clicked")
                //changeFragment(Workouts())
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun navToWeight(view: android.view.View) {

        val intent = Intent(this, WeightTracker::class.java)
        startActivity(intent)
        finish()
    }


}


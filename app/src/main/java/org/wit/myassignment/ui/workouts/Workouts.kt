package org.wit.myassignment.ui.workouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.snackbar.Snackbar
import org.wit.myassignment.R
import org.wit.myassignment.databinding.TrainerBinding
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.models.TrainerModel
import timber.log.Timber.i

class Workouts  : AppCompatActivity() {
    private lateinit var binding: TrainerBinding
    var plan = TrainerModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var edit = false
        setContentView(R.layout.trainer)


        binding = TrainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp

            binding.planTitle.setText(plan.title)



        if (intent.hasExtra("plan_edit")) {
            edit = true
            plan = intent.extras?.getParcelable("plan_edit")!!
            binding.planTitle.setText(plan.title)
            binding.btnAdd.setText(R.string.save_plan)
            binding.btnDeletePlan.setVisibility(View.VISIBLE)
            binding.btnDeletePlan.setOnClickListener() {
                app.plans.delete(plan)

                setResult(RESULT_OK)
                finish()
            }
        }

        binding.btnAdd.setOnClickListener() {
            plan.title = binding.planTitle.text.toString()
            if (plan.title.isEmpty()) {
                Snackbar.make(it, R.string.enter_plan_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.plans.update(plan.copy())
                } else {
                    app.plans.create(plan.copy())
                }
            }
            i("add Button Pressed: $plan")
            setResult(RESULT_OK)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_plan, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }

}



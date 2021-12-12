package org.wit.myassignment.ui.weighttracking

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.weights.*
import org.wit.myassignment.R
import org.wit.myassignment.databinding.WeightsBinding
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.models.WeightModel
import org.wit.myassignment.ui.data.WeightData
import org.wit.myassignment.ui.home.Home
import timber.log.Timber
import timber.log.Timber.i

class Weights  : AppCompatActivity() {
    private lateinit var binding: WeightsBinding
    private lateinit var database : DatabaseReference
    var weight = WeightModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var edit = false

        setContentView(R.layout.weights_list)

        binding = WeightsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAddRoutine.title = title
        setSupportActionBar(binding.toolbarAddRoutine)
        app = application as MainApp

        binding.currentWeight.setText(weight.currentWeight)
        binding.dayOfMeasurement.setText(weight.dayOfMeasurement)

        if (intent.hasExtra("routine_Edit")) {
            edit = true
            weight = intent.extras?.getParcelable("routine_Edit")!!
            binding.currentWeight.setText(weight.currentWeight)
            binding.dayOfMeasurement.setText(weight.dayOfMeasurement)
            binding.btnAdd.setText(R.string.save_routine)
            binding.btnDeletePlan.setVisibility(View.VISIBLE)
            binding.btnDeletePlan.setOnClickListener() {
                //app.weights.delete(weight)
                setResult(RESULT_OK)
                finish()
            }
        }
        binding.btnAdd.setOnClickListener() {

            weight.currentWeight = binding.currentWeight.text.toString()
            weight.dayOfMeasurement = binding.dayOfMeasurement.text.toString()
            if (weight.currentWeight.isEmpty()) {
                i("add Button Pressed: ${weight}")
                Snackbar.make(it,R.string.enter_routine_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    //app.weights.update(weight.copy())
                    i("add Button Pressed: ${weight}")
                } else {

                    //firebase link
                    val currentWeight = binding.currentWeight.text.toString()
                    val dayOfMeasurement = binding.dayOfMeasurement.text.toString()
                    database = FirebaseDatabase.getInstance().getReference("weightData")

                    val WeightData = WeightData(currentWeight, dayOfMeasurement)

                    database.child("Day of Measurement : $dayOfMeasurement").setValue(WeightData).addOnSuccessListener {

                        Timber.i("Entered data base ${database}")

                        Toast.makeText(this, "Sucessfully saved", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener{
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    }
                    //app.weights.create(weight.copy())
                    i("add Button Pressed: ${weight}")
                }
            }
            setResult(RESULT_OK)
            val intent = Intent(this, WeightList::class.java)
            startActivity(intent)
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

}

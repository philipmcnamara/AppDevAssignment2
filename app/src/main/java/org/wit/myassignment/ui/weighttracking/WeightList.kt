package org.wit.myassignment.ui.weighttracking

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.myassignment.R
import org.wit.myassignment.adapters.RoutineAdapter
import org.wit.myassignment.adapters.RoutineListener
import org.wit.myassignment.databinding.WeightsListBinding
import org.wit.myassignment.main.MainApp
import org.wit.myassignment.models.WeightModel
import org.wit.myassignment.ui.home.Home
import timber.log.Timber
import timber.log.Timber.i


class WeightList : AppCompatActivity(), RoutineListener {

    lateinit var app: MainApp
    private lateinit var binding: WeightsListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WeightsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarRoutine.title = title
        setSupportActionBar(binding.toolbarRoutine)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewRoutine.layoutManager = layoutManager
        binding.recyclerViewRoutine.adapter = RoutineAdapter(app.routines.findAll(),this)

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, Weights::class.java)
                startActivityForResult(launcherIntent,0)
            }
            R.id.item_add -> {
                i("Button Clicked")
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

    override fun onPlanClick(weight: WeightModel) {
        val launcherIntent = Intent(this, Weights::class.java)
        launcherIntent.putExtra("routine_edit", weight)
        i("Add Button Pressed")
        startActivityForResult(launcherIntent, 0)
    }

}

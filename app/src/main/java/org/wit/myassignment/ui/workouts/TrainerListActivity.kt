package org.wit.myassignment.ui.workouts

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.myassignment.databinding.ActivityTrainerListBinding
import org.wit.myassignment.main.MainApp
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import org.wit.myassignment.R
import org.wit.myassignment.adapters.PlanListener
import org.wit.myassignment.adapters.TrainerAdapter
import org.wit.myassignment.models.TrainerModel
import org.wit.myassignment.ui.home.Home
import timber.log.Timber
import java.util.*


class TrainerListActivity : AppCompatActivity(), PlanListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityTrainerListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    //val plans = ArrayList<TrainerModel>()
    var plansList: ArrayList<TrainerModel> = ArrayList()


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        app = application as MainApp
        var plans = app.plans.findAll()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            //not required as I want the search to work on type not click
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                plansList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()) {
                    plans.forEach{
                        if(it.title.lowercase(Locale.getDefault()).contains(searchText)){
                            plansList.add(it)
                            Timber.i("plansList: $it")
                        }
                    }
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                } else{
                    // plansList.clear()
                    plansList.addAll(plans)
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                }
                loadPlansList()
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        loadPlans()

        registerRefreshCallback()



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, TrainerActivity::class.java)
                startActivityForResult(launcherIntent,0)
            }
            R.id.item_add -> {
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


    override fun onPlanClick(plan: TrainerModel) {
        val launcherIntent = Intent(this, TrainerActivity::class.java)
        launcherIntent.putExtra("plan_edit", plan)
        refreshIntentLauncher.launch(launcherIntent)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadPlans() {
        showPlans(app.plans.findAll())
    }
    private fun loadPlansList() {
        showPlansList(plansList)
    }
    fun showPlansList (plansList: List<TrainerModel>) {
        binding.recyclerView.adapter = TrainerAdapter(plansList, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
    fun showPlans (plans: List<TrainerModel>) {
        binding.recyclerView.adapter = TrainerAdapter(plans, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { loadPlans() }
    }

}
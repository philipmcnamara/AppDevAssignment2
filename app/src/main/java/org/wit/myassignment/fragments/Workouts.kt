package org.wit.myassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.wit.myassignment.R

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.myassignment.databinding.ActivityTrainerListBinding
import org.wit.myassignment.main.MainApp
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import org.wit.myassignment.activities.TrainerActivity
import org.wit.myassignment.activities.TrainerListActivity
import org.wit.myassignment.adapters.PlanListener
import org.wit.myassignment.adapters.TrainerAdapter
import org.wit.myassignment.models.TrainerModel
import timber.log.Timber
import timber.log.Timber.i
import java.util.*



class Workouts : Fragment(), PlanListener  {


    lateinit var app: MainApp
    private lateinit var binding: ActivityTrainerListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    //val plans = ArrayList<TrainerModel>()
    var plansList: ArrayList<TrainerModel> = ArrayList()

    private fun Fragment.onCreateOptionsMenu(menu: Menu) {
        //menuInflater.inflate(R.menu.menu_main, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        // app = application as MainApp
        var plans = app.plans.findAll()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            //not required as I want the search to work on type not click
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

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
        return onCreateOptionsMenu(menu)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workouts, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainerListBinding.inflate(layoutInflater)

       // val layoutManager = LinearLayoutManager(this)
       // binding.recyclerView.layoutManager = layoutManager

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
                i("Button Clicked")
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

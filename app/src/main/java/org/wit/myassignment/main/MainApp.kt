package org.wit.myassignment.main

import android.app.Application
import org.wit.myassignment.models.*
//import org.wit.myassignment.ui.data.FireStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

   // lateinit var users: UserStore
   //var weights = WeightMemStore()
    lateinit var plans: PlanStore
    lateinit var weights: WeightStore
    var users = UserMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        plans = PlanJSONStore(applicationContext)
       // users = UserJSONStore(applicationContext)
        //plans = PlanMemStore()
        users = UserMemStore()
        //weights = FireStore(applicationContext)
        //weights = WeightMemStore()
        i("Gym Trainer started")
    }
}
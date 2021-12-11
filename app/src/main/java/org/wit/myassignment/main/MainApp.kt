package org.wit.myassignment.main

import android.app.Application
import org.wit.myassignment.models.*
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

   // lateinit var users: UserStore
   var weights = WeightMemStore()
    lateinit var plans: PlanStore
    var users = UserMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        plans = PlanJSONStore(applicationContext)
       // users = UserJSONStore(applicationContext)
        //plans = PlanMemStore()
        users = UserMemStore()
        weights = WeightMemStore()
        i("Gym Trainer started")
    }
}
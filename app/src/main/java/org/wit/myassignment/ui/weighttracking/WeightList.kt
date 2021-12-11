package org.wit.myassignment.ui.weighttracking

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import org.wit.myassignment.R
import org.wit.myassignment.adapters.WeightAdapter
import org.wit.myassignment.models.WeightModel
import org.wit.myassignment.ui.data.WeightData
import org.wit.myassignment.ui.home.Home
import timber.log.Timber
import timber.log.Timber.i


class WeightList : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var weightRecyclerview : RecyclerView
    private lateinit var weightArrayList : ArrayList<WeightData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weights_list)

        weightRecyclerview = findViewById(R.id.weightList)
        weightRecyclerview.layoutManager = LinearLayoutManager(this)
        weightRecyclerview.setHasFixedSize(true)

        weightArrayList = arrayListOf<WeightData>()
        getUserData()

    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("weightData")
        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val weight = userSnapshot.getValue(WeightData::class.java)
                        weightArrayList.add(weight!!)
                    }
                    weightRecyclerview.adapter = WeightAdapter(weightArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }


    fun navToWeight(view: android.view.View) {
        val intent = Intent(this, WeightTracker::class.java)
        startActivity(intent)
        finish()
    }

    fun Home(view: android.view.View) {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }



}

package org.wit.myassignment.ui.weighttracking

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.trainer_list.*
import org.wit.myassignment.R
import org.wit.myassignment.adapters.WeightAdapter
import org.wit.myassignment.ui.data.WeightData
import org.wit.myassignment.ui.home.Home
import timber.log.Timber


class WeightList : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var weightRecyclerview : RecyclerView
    private lateinit var weightArrayList : ArrayList<WeightData>
    lateinit var loader : AlertDialog
    private lateinit var weightViewModel : WeightViewModel

    var database: DatabaseReference = FirebaseDatabase.getInstance().reference


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
/*
    fun deleteWeight(viewHolder: RecyclerView.ViewHolder){

        dbref = FirebaseDatabase.getInstance().getReference("weightData")

            val adapter = recyclerView.adapter as WeightAdapter
            adapter.removeAt(viewHolder.adapterPosition)
    }

 */

    fun deleteWeight(viewHolder: RecyclerView.ViewHolder){

        dbref = FirebaseDatabase.getInstance().getReference("weightData")
        Timber.i("Database ref : $dbref")
/*
        val childDelete : MutableMap<String, Any?> = HashMap()
        childDelete["/Day of Measurement : $dbref"] = null



        dbref.updateChildren(childDelete)
 */
        val adapter = recyclerView.adapter as WeightAdapter

        adapter.removeAt(viewHolder.adapterPosition)
        weightViewModel.delete(weightViewModel.dayOfMeasurement)


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
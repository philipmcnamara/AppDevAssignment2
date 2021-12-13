package org.wit.myassignment.ui.weighttracking

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.weights.*
import org.wit.myassignment.R
import org.wit.myassignment.adapters.WeightAdapter
import org.wit.myassignment.ui.data.WeightData
import org.wit.myassignment.ui.home.Home


class WeightList : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var weightRecyclerview : RecyclerView
    private lateinit var weightArrayList : ArrayList<WeightData>
    lateinit var loader : AlertDialog





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

    fun deleteWeight(dayOfRecording : String){

        dbref = FirebaseDatabase.getInstance().getReference("weightData")
        dbref.child(dayOfRecording).get().addOnSuccessListener {

            if (it.exists()){

                dbref.child(dayOfRecording).removeValue()
                dbref.child(currentWeight.toString()).removeValue()

            }else{
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
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
package org.wit.myassignment.ui.weighttracking

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import org.wit.myassignment.R
import org.wit.myassignment.adapters.WeightAdapter
import org.wit.myassignment.firebase.FirebaseDBManager.delete
import org.wit.myassignment.models.WeightStore
import org.wit.myassignment.ui.data.WeightData
import org.wit.myassignment.ui.home.Home
import org.wit.myassignment.utils.SwipeToDeleteCallback
import org.wit.myassignment.utils.hideLoader
import org.wit.myassignment.utils.showLoader
import timber.log.Timber
import java.lang.Exception
import java.nio.file.Files.delete


class WeightList : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var weightRecyclerview : RecyclerView
    private lateinit var weightArrayList : ArrayList<WeightData>
    lateinit var loader : AlertDialog

    private lateinit var  weightViewModel : WeightViewModel


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

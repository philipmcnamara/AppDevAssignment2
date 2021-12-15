package org.wit.myassignment.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import org.wit.myassignment.models.WeightStore
import org.wit.myassignment.ui.data.WeightData
import timber.log.Timber

object FirebaseDBManager : WeightStore {

    var database: DatabaseReference = FirebaseDatabase.getInstance().reference


    override fun findAll(weightsList: MutableLiveData<List<WeightData>>) {
        TODO("Not yet implemented")
    }

    override fun findAll(userid: String, weightsList: MutableLiveData<List<WeightData>>) {

        database.child("user-weightData").child(userid)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase Weight error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<WeightData>()
                    val children = snapshot.children
                    children.forEach {
                        val weight = it.getValue(WeightData::class.java)
                        localList.add(weight!!)
                    }
                    database.child("user-weightData").child(userid)
                        .removeEventListener(this)

                    weightsList.value = localList
                }
            })
    }

    override fun findById(userid: String, weightid: String, weight: MutableLiveData<WeightData>) {
        database.child("user-weightData").child(userid)
            .child(weightid).get().addOnSuccessListener {
                weight.value = it.getValue(WeightData::class.java)
                Timber.i("firebase Got value ${it.value}")
            }.addOnFailureListener{
                Timber.e("firebase Error getting data $it")
            }
    }

    override fun create(firebaseUser: MutableLiveData<FirebaseUser>, weight: WeightData) {
        Timber.i("Firebase DB Reference : $database")

        val uid = firebaseUser.value!!.uid
        val key = database.child("weights").push().key
        if (key == null) {
            Timber.i("Firebase Error : Key Empty")
            return
        }
        //weight.uid = key
        val weightValues = weight.toMap()

        val childAdd = HashMap<String, Any>()
        childAdd["/weights/$key"] = weightValues
        childAdd["/user-weightData/$uid/$key"] = weightValues

        database.updateChildren(childAdd)
    }

    override fun delete(dayOfMeasurement: String) {
        database.child("weightData").child(dayOfMeasurement)
        val childDelete : MutableMap<String, Any?> = HashMap()
        childDelete["/weightData/$dayOfMeasurement"] = null
        database.updateChildren(childDelete)
    }

    override fun update(userid: String, weightid: String, weight: WeightData) {
        val weightValues = weight.toMap()

        val childUpdate : MutableMap<String, Any?> = HashMap()
        childUpdate["weights/$weightid"] = weightValues
        childUpdate["user-weightData/$userid/$weightid"] = weightValues

        database.updateChildren(childUpdate)
    }


}
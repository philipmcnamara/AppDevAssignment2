package org.wit.myassignment.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import org.wit.myassignment.models.WeightModel
import org.wit.myassignment.models.WeightStore
import timber.log.Timber

object FirebaseDBManager : WeightStore{

    var database: DatabaseReference = FirebaseDatabase.getInstance().reference


    override fun findAll(weightsList: MutableLiveData<List<WeightModel>>) {
        TODO("Not yet implemented")
    }

    override fun findAll(userid: String, weightsList: MutableLiveData<List<WeightModel>>) {
        database.child("weightData").child(userid)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase Donation error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<WeightModel>()
                    val children = snapshot.children
                    children.forEach {
                        val weight = it.getValue(WeightModel::class.java)
                        localList.add(weight!!)
                    }
                    database.child("weightData").child(userid)
                        .removeEventListener(this)

                    weightsList.value = localList
                }
            })
    }

    override fun findById(userid: String, weightid: String, weight: MutableLiveData<WeightModel>) {
        TODO("Not yet implemented")
    }

    override fun create(firebaseUser: MutableLiveData<FirebaseUser>, weight: WeightModel) {
        Timber.i("Firebase DB Reference : $database")

        val uid = firebaseUser.value!!.uid
        val key = database.child("donations").push().key
        if (key == null) {
            Timber.i("Firebase Error : Key Empty")
            return
        }
        weight.uid = key
        val donationValues = weight.toMap()

        val childAdd = HashMap<String, Any>()
        childAdd["/weightData/$key"] = donationValues
        childAdd["/user-weightData/$uid/$key"] = donationValues

        database.updateChildren(childAdd)
    }

    override fun delete(userid: String, weightid: Long) {
        val childDelete : MutableMap<String, Any?> = HashMap()
        childDelete["/weightData/$weightid"] = null
        childDelete["/user-weightData/$userid/$weightid"] = null

        database.updateChildren(childDelete)
    }

    override fun update(userid: String, weightid: String, weight: WeightModel) {
        TODO("Not yet implemented")
    }

}
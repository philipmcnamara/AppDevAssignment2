package org.wit.myassignment.ui.data

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.wit.myassignment.models.WeightStore
/*
class FireStore (val context: Context) : WeightStore {
var weights = ArrayList<WeightData>()
lateinit var userId: String
lateinit var db: DatabaseReference


override suspend fun findAll(): List<WeightData> {
    return weights
}

 override suspend fun create(weight: WeightData) {
    val key = db.child("users").child(userId).child("weightData").push().key
    key?.let {
        //weight.fbId = key
        weights.add(weight)
        db.child("users").child(userId).child("weightData").child(key).setValue(weight)
    }
}

override suspend fun update(weight: WeightData) {
    var foundWeight: WeightData? = weights.find { p -> p.fbId == weight.fbId }
    if (foundWeight != null) {
        foundWeight.currentWeight = weight.currentWeight
        foundWeight.dayOfMeasurement = weight.dayOfMeasurement
    }

    weight.fbId?.let { db.child("users").child(userId).child("weightData").child(it).setValue(weight) }

}

override suspend fun findById(id: Long): WeightData? {
    return weights.find { p -> p.id == id }
}

override suspend fun delete(weight: WeightData) {
    weight.fbId?.let { db.child("users").child(userId).child("weightData").child(it).removeValue() }
    weights.remove(weight)
}

override suspend fun clear() {
    weights.clear()
}

fun fetchWeights(weightsReady: () -> Unit) {
    val valueEventListener = object : ValueEventListener {
        override fun onCancelled(dataSnapshot: DatabaseError) {
        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            dataSnapshot!!.children.mapNotNullTo(weights) {
                it.getValue<WeightData>(
                    WeightData::class.java
                )
            }
            weightsReady()
        }
    }
    userId = FirebaseAuth.getInstance().currentUser!!.uid
    db = FirebaseDatabase.getInstance("https://appdevassignment2-b4925-default-rtdb.firebaseio.com/weightData").reference
    weights.clear()
    db.child("users").child(userId).child("weightData")
        .addListenerForSingleValueEvent(valueEventListener)
}


}

 */
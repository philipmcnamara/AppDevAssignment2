package org.wit.myassignment.models

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import org.wit.myassignment.ui.data.WeightData

/*
interface WeightStore {
    fun findAll(): List<WeightModel>
    fun create(plan: WeightModel)
    fun update(plan: WeightModel)
    fun delete(plan: WeightModel)
    fun delete(id: String)
}



interface WeightStore {
    fun findAll(weightsList:
                MutableLiveData<List<WeightData>>): MutableLiveData<List<WeightData>>

    fun findAll(userid:String,
                weightsList:
                MutableLiveData<List<WeightData>>)
    fun findById(userid:String, weightid: String,
                 weight: MutableLiveData<WeightData>)
    fun create(firebaseUser: MutableLiveData<FirebaseUser>, weight: WeightData)
    fun update(userid:String, weightid: String, weight: WeightData)
    fun delete( dayOfMeasurement: String)
}
 */


interface WeightStore {
    suspend fun findAll(): List<WeightData>
    suspend fun create(weight: WeightData)
    suspend fun update(weight: WeightData)
    suspend fun findById(id:Long) : WeightData?
    suspend fun delete(weight: WeightData)
    suspend fun clear()
}

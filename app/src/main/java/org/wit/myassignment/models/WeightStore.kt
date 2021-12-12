package org.wit.myassignment.models

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

/*
interface WeightStore {
    fun findAll(): List<WeightModel>
    fun create(plan: WeightModel)
    fun update(plan: WeightModel)
    fun delete(plan: WeightModel)
    fun delete(id: String)
}

 */

interface WeightStore {
    fun findAll(weightsList:
                MutableLiveData<List<WeightModel>>)
    fun findAll(userid:String,
                weightsList:
                MutableLiveData<List<WeightModel>>)
    fun findById(userid:String, weightid: String,
                 weight: MutableLiveData<WeightModel>)
    fun create(firebaseUser: MutableLiveData<FirebaseUser>, weight: WeightModel)
    fun delete(userid:String, weightid: Long)
    fun update(userid:String, weightid: String, weight: WeightModel)
}

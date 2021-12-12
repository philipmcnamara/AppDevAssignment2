package org.wit.myassignment.models

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser


interface WeightStore {
    fun findAll(): List<WeightModel>
    fun create(plan: WeightModel)
    fun update(plan: WeightModel)
    fun delete(plan: WeightModel)
    fun delete(id: String)
}





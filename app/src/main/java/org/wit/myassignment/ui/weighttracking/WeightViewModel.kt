package org.wit.myassignment.ui.weighttracking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.wit.myassignment.firebase.FirebaseDBManager
import org.wit.myassignment.ui.data.WeightData
import timber.log.Timber
import java.lang.Exception

class WeightViewModel : ViewModel() {

    var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    val dayOfMeasurement = FirebaseDBManager.database.child("weightData")

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    fun addWeight(firebaseUser: MutableLiveData<FirebaseUser>, weight: WeightData) {
        status.value = try {
            FirebaseDBManager.create(firebaseUser,weight)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun delete(dayOfMeasurement: DatabaseReference) {
        FirebaseDBManager.database.child("weightData")
        val childDelete : MutableMap<String, Any?> = HashMap()
        childDelete["/Day of Measurement : $dayOfMeasurement"] = null

        try {
            //DonationManager.delete(userid,id)
            val childDelete : MutableMap<String, Any?> = HashMap()
            childDelete["/Day of Measurement : $dayOfMeasurement"] = null
            Timber.i("Report Delete Success")
        }
        catch (e: Exception) {
            Timber.i("Report Delete Error : $e.message")
        }
    }

}
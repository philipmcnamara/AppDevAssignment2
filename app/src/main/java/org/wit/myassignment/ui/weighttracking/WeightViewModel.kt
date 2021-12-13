package org.wit.myassignment.ui.weighttracking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import org.wit.myassignment.firebase.FirebaseDBManager
import org.wit.myassignment.ui.data.WeightData

class WeightViewModel : ViewModel() {

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

}
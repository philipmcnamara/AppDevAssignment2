package org.wit.myassignment.ui.weighttracking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import org.wit.myassignment.firebase.FirebaseDBManager
import org.wit.myassignment.models.WeightModel
import org.wit.myassignment.models.theLastId
import timber.log.Timber
import java.lang.Exception

class WeightViewModel : ViewModel() {

    private val weightsList =
        MutableLiveData<List<WeightModel>>()

    val observableWeightsList: LiveData<List<WeightModel>>
        get() = weightsList

    var liveFirebaseUser = MutableLiveData<FirebaseUser>()

    init { load() }

    fun load() {
        try {
            //DonationManager.findAll(liveFirebaseUser.value?.email!!, donationsList)
            FirebaseDBManager.findAll(liveFirebaseUser.value?.uid!!,
                weightsList)
            Timber.i("Report Load Success : ${weightsList.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Report Load Error : $e.message")
        }
    }

    /*fun delete(userid: String, id: String) {
        try {
            //DonationManager.delete(userid,id)
            FirebaseDBManager.delete(userid,id)
            Timber.i("Report Delete Success")
        }
        catch (e: Exception) {
            Timber.i("Report Delete Error : $e.message")
        }
    }

     */

    fun delete(userid: String) {
        try {
            //DonationManager.delete(userid,id)
            FirebaseDBManager.delete(userid, theLastId)
            Timber.i("Report Delete Success")
        }
        catch (e: Exception) {
            Timber.i("Report Delete Error : $e.message")
        }
    }
}
package org.wit.myassignment.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize


@Parcelize
data class TrainerModel(var id: Long = 0,
                        var title: String = "") : Parcelable

@Parcelize
data class WeightModel (
    var id: Long = 0,
    var currentWeight: String = "",
    var dayOfMeasurement: String = ""
    ) : Parcelable


/*
@Parcelize
data class WeightModel(
    var uid: String? = "",
    var currentWeight: String = "N/A",
    var dayOfMeasurement: String = "N/A",
    var email: String? = "joe@bloggs.com")
    : Parcelable
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "currentWeight" to currentWeight,
            "dayOfMeasurement" to dayOfMeasurement,
            "email" to email
        )
    }
}

 */
package org.wit.myassignment.ui.data

/*

data class WeightData(var currentWeight : String ?= null,var dayOfMeasurement : String ?= null)

 */

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class WeightData(
    var currentWeight: String ?= null,
    var dayOfMeasurement: String ?= null)
    : Parcelable
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "currentWeight" to currentWeight,
            "dayOfMeasurement" to dayOfMeasurement,
        )
    }
}
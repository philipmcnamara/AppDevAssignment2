package org.wit.myassignment.ui.data

/*

data class WeightData(var currentWeight : String ?= null,var dayOfMeasurement : String ?= null)

 */

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

/*
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



data class WeightData(@PrimaryKey(autoGenerate = true)
                      var id: Long = 0,
                      var fbId: String? = "",
                      var currentWeight: String? = "",
                      var dayOfMeasurement: String? = ""): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(fbId)
        parcel.writeString(currentWeight)
        parcel.writeString(dayOfMeasurement)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeightData> {
        override fun createFromParcel(parcel: Parcel): WeightData {
            return WeightData(parcel)
        }

        override fun newArray(size: Int): Array<WeightData?> {
            return arrayOfNulls(size)
        }
    }
}
 */


@IgnoreExtraProperties
@Parcelize
data class WeightData(@PrimaryKey(autoGenerate = true)
                      //var id: Long = 0,
                      var fbId: String? = "",
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
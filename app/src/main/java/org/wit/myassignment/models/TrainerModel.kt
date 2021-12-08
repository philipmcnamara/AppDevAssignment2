package org.wit.myassignment.models

import android.os.Parcelable
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


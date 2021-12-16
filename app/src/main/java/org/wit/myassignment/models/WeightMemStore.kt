package org.wit.myassignment.models

import org.wit.myassignment.ui.data.WeightData
import timber.log.Timber.i

var theLastId = 0L

internal fun getRoutineId(): Long {
    return theLastId++
}
/*
class WeightMemStore : WeightStore {

    val weights = ArrayList<WeightData>()

    override suspend fun findAll(): List<WeightData> {
        return weights
    }

    override suspend fun create(weight: WeightData) {
        weight.id = getId()
        weights.add(weight)
        logAll()
    }

    override suspend fun update(weight: WeightData) {
        val foundPlacemark: WeightData? = weights.find { p -> p.id == weight.id }
        if (foundPlacemark != null) {
            foundPlacemark.currentWeight = weight.currentWeight
            foundPlacemark.dayOfMeasurement = weight.dayOfMeasurement
            logAll()
        }
    }
    override suspend fun delete(weight: WeightData) {
        weights.remove(weight)
        logAll()
    }

    private fun logAll() {
        weights.forEach { i("$it") }
    }
    override suspend fun findById(id:Long) : WeightData? {
        val foundWeight: WeightData? = weights.find { it.id == id }
        return foundWeight
    }
    override suspend fun clear(){
        weights.clear()
    }
}

 */




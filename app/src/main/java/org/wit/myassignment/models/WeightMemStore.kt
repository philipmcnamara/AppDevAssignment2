package org.wit.myassignment.models

import timber.log.Timber.i

var theLastId = 0L

internal fun getRoutineId(): Long {
    return theLastId++
}
/*

class WeightMemStore : WeightStore {

    val weights = ArrayList<WeightModel>()

    override fun findAll() : List<WeightModel>{
        return weights
    }

    override fun create(weight: WeightModel){
        weight.id = getRoutineId()
        weights.add(weight)
        logAll()
    }

    override fun update(weight: WeightModel) {
        var foundPlan: WeightModel? = weights.find { p -> p.id == weight.id }
        if(foundPlan != null){
            foundPlan.currentWeight = weight.currentWeight
            logAll()
        }
    }

    fun logAll() {
        weights.forEach{ i("${it}") }
    }

    override fun delete(weight: WeightModel) {
        weights.remove(weight)
        logAll()
    }

    override fun delete(id: String) {
        TODO("Not yet implemented")
    }

}

 */
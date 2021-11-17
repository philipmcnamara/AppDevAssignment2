package org.wit.myassignment.models

import timber.log.Timber.i

var theLastId = 0L

internal fun getRoutineId(): Long {
    return theLastId++
}

class RoutineMemStore : RoutineStore {

    val routines = ArrayList<exerciseModel>()

    override fun findAll() : List<exerciseModel>{
        return routines
    }

    override fun create(routine: exerciseModel){
        routine.id = getRoutineId()
        routines.add(routine)
        logAll()
    }

    override fun update(routine: exerciseModel) {
        var foundPlan: exerciseModel? = routines.find { p -> p.id == routine.id }
        if(foundPlan != null){
            foundPlan.title = routine.title
            logAll()
        }
    }

    fun logAll() {
        routines.forEach{ i("${it}") }
    }

}
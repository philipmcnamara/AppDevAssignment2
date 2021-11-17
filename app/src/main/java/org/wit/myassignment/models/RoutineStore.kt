package org.wit.myassignment.models

interface RoutineStore {
    fun findAll(): List<exerciseModel>
    fun create(plan: exerciseModel)
    fun update(plan: exerciseModel)
}

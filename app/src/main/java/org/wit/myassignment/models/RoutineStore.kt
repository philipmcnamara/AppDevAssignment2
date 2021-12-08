package org.wit.myassignment.models

interface RoutineStore {
    fun findAll(): List<WeightModel>
    fun create(plan: WeightModel)
    fun update(plan: WeightModel)
    fun delete(plan: WeightModel)
}

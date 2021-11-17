package org.wit.myassignment.models

interface PlanStore {
    fun findAll(): List<TrainerModel>
    fun create(plan: TrainerModel)
    fun update(plan: TrainerModel)
    fun delete(plan: TrainerModel)
}


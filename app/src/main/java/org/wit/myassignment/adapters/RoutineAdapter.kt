package org.wit.myassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.myassignment.databinding.CardRoutineBinding
import org.wit.myassignment.models.exerciseModel

interface RoutineListener {
    fun onPlanClick(plan: exerciseModel)
}

class RoutineAdapter constructor(private var routines: List<exerciseModel>,
                                 private val listener: RoutineListener) :
    RecyclerView.Adapter<RoutineAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRoutineBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val routine = routines[holder.adapterPosition]
        holder.bind(routine, listener)
    }

    override fun getItemCount(): Int = routines.size

    class MainHolder(private val binding : CardRoutineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(routine: exerciseModel, listener: RoutineListener) {
            binding.planRoutine.text = routine.title
            binding.root.setOnClickListener { listener.onPlanClick(routine) }
        }
    }
}
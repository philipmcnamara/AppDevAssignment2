package org.wit.myassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.myassignment.databinding.CardRoutineBinding
import org.wit.myassignment.models.WeightModel

interface RoutineListener {
    fun onPlanClick(plan: WeightModel)
}

class RoutineAdapter constructor(private var weights: List<WeightModel>,
                                 private val listener: RoutineListener) :
    RecyclerView.Adapter<RoutineAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRoutineBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val routine = weights[holder.adapterPosition]
        holder.bind(routine, listener)
    }

    override fun getItemCount(): Int = weights.size

    class MainHolder(private val binding : CardRoutineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weight: WeightModel, listener: RoutineListener) {
            binding.planRoutine.text = weight.currentWeight
            binding.root.setOnClickListener { listener.onPlanClick(weight) }
        }
    }
}
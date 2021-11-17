package org.wit.myassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.myassignment.databinding.CardTrainerBinding
import org.wit.myassignment.models.TrainerModel

interface PlanListener {
    fun onPlanClick(plan: TrainerModel)
}

class TrainerAdapter constructor(private var plansList: List<TrainerModel>,
                                 private val listener: PlanListener) :
    RecyclerView.Adapter<TrainerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardTrainerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val plan = plansList[holder.adapterPosition]
        holder.bind(plan, listener)
    }

    override fun getItemCount(): Int = plansList.size

    class MainHolder(private val binding : CardTrainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(plan: TrainerModel, listener: PlanListener) {
            binding.planTitle.text = plan.title
            binding.root.setOnClickListener { listener.onPlanClick(plan) }

        }
    }
}
package org.wit.myassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import org.wit.myassignment.R
import org.wit.myassignment.ui.data.WeightData


class WeightAdapter(private val weightList : ArrayList<WeightData>) : RecyclerView.Adapter<WeightAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.weight_item,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = weightList[position]

        holder.currentWeight.text = currentitem.currentWeight
        holder.dayOfMeasurement.text = currentitem.dayOfMeasurement

    }

    override fun getItemCount(): Int {
        return weightList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val currentWeight : TextView = itemView.findViewById(R.id.weightCurrent)
        val dayOfMeasurement : TextView = itemView.findViewById(R.id.weightDay)

    }
}
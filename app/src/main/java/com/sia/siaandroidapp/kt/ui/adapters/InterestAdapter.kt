package com.sia.siaandroidapp.java.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sia.siaandroidapp.R
import com.sia.siaandroidapp.java.model.Interest

class InterestAdapter(private val interest: List<Interest>) : RecyclerView.Adapter<InterestAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tvInterest)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.interest_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return interest.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val interest: Interest = interest[position]
        holder.name.text = interest.interest
    }
}
package com.example.shilpakalashowcase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TimelineAdapter(private val stages: List<WipStage>) :
    RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>() {

    inner class TimelineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvStageNumber: TextView = view.findViewById(R.id.tvStageNumber)
        val ivStageImage: ImageView = view.findViewById(R.id.ivStageImage)
        val tvStageDescription: TextView = view.findViewById(R.id.tvStageDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_timeline_stage, parent, false)
        return TimelineViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        val stage = stages[position]
        holder.tvStageNumber.text = stage.stageNumber.toString()
        holder.tvStageDescription.text = stage.description

        Glide.with(holder.itemView.context)
            .load(stage.imageUrl)
            .centerCrop()
            .into(holder.ivStageImage)
    }

    override fun getItemCount() = stages.size
}
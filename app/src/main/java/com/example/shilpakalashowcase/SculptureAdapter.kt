package com.example.shilpakalashowcase

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SculptureAdapter(private val sculptures: List<Sculpture>, private val artistId: String) :
    RecyclerView.Adapter<SculptureAdapter.SculptureViewHolder>() {

    inner class SculptureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivSculpture: ImageView = view.findViewById(R.id.ivSculpture)
        val tvTitle: TextView = view.findViewById(R.id.tvSculptureTitle)
        val tvMaterial: TextView = view.findViewById(R.id.tvMaterial)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvWipBadge: TextView = view.findViewById(R.id.tvWipBadge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SculptureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sculpture, parent, false)
        return SculptureViewHolder(view)
    }

    override fun onBindViewHolder(holder: SculptureViewHolder, position: Int) {
        val sculpture = sculptures[position]
        holder.tvTitle.text = sculpture.title
        holder.tvMaterial.text = sculpture.material
        holder.tvPrice.text = sculpture.price

        // Show WIP badge if applicable
        holder.tvWipBadge.visibility =
            if (sculpture.isWorkInProgress) View.VISIBLE else View.GONE

        Glide.with(holder.itemView.context)
            .load(sculpture.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.sculpture_placeholder)
            .into(holder.ivSculpture)

        // Click → go to image detail
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ImageDetailActivity::class.java)
            intent.putExtra("SCULPTURE_PRODUCT_ID", sculpture.productId)
            intent.putExtra("ARTIST_ID", artistId)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = sculptures.size
}
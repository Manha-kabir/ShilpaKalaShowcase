package com.example.shilpakalashowcase

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ArtistAdapter(private val artists: List<Artist>) :
    RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    inner class ArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProfile: ImageView = view.findViewById(R.id.ivArtistProfile)
        val tvName: TextView = view.findViewById(R.id.tvArtistName)
        val tvSpecialty: TextView = view.findViewById(R.id.tvSpecialty)
        val tvLocation: TextView = view.findViewById(R.id.tvLocation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artist, parent, false)
        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artists[position]
        holder.tvName.text = artist.name
        holder.tvSpecialty.text = artist.specialty
        holder.tvLocation.text = "📍 ${artist.location}"

        Glide.with(holder.itemView.context)
            .load(artist.profileImageUrl)
            .centerCrop()
            .placeholder(R.drawable.artist_placeholder)
            .into(holder.ivProfile)

        // Click → go to artist portfolio
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ArtistPortfolioActivity::class.java)
            intent.putExtra("ARTIST_ID", artist.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = artists.size
}
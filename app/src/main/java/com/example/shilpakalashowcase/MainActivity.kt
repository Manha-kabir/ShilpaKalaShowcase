package com.example.shilpakalashowcase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val rvArtists = findViewById<RecyclerView>(R.id.rvArtists)
        rvArtists.layoutManager = GridLayoutManager(this, 2)
        rvArtists.adapter = ArtistAdapter(SampleData.artists)
    }
}
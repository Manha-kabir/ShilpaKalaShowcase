package com.example.shilpakalashowcase

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistPortfolioActivity : AppCompatActivity() {

    private lateinit var artist: Artist
    private lateinit var rvSculptures: RecyclerView
    private lateinit var panelTimeline: View
    private lateinit var panelHeritage: View
    private lateinit var tvHeritageTitle: TextView
    private lateinit var tvHeritageContent: TextView
    private lateinit var progressHeritage: ProgressBar
    private lateinit var rvTimeline: RecyclerView
    private lateinit var tvTimelineTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_portfolio)
        supportActionBar?.hide()

        val artistId = intent.getStringExtra("ARTIST_ID")
        artist = SampleData.artists.find { it.id == artistId }!!

        findViewById<TextView>(R.id.tvArtistName).text = artist.name
        findViewById<TextView>(R.id.tvArtistLocation).text = artist.location
        rvSculptures = findViewById(R.id.rvSculptures)
        panelTimeline = findViewById(R.id.panelTimeline)
        panelHeritage = findViewById(R.id.panelHeritage)
        tvHeritageTitle = findViewById(R.id.tvHeritageTitle)
        tvHeritageContent = findViewById(R.id.tvHeritageContent)
        progressHeritage = findViewById(R.id.progressHeritage)

        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }

        rvSculptures.layoutManager = GridLayoutManager(this, 2)
        rvSculptures.adapter = SculptureAdapter(artist.sculptures, artist.id)

        setupTabs()
        rvTimeline = findViewById(R.id.rvTimeline)
        tvTimelineTitle = findViewById(R.id.tvTimelineTitle)
    }

    private fun setupTabs() {
        val tabGallery = findViewById<TextView>(R.id.tabGallery)
        val tabTimeline = findViewById<TextView>(R.id.tabTimeline)
        val tabHeritage = findViewById<TextView>(R.id.tabHeritage)

        fun setActive(active: TextView, others: List<TextView>) {
            active.setTextColor(getColor(R.color.accent))
            active.setBackgroundResource(R.drawable.bg_tab_selected)
            others.forEach {
                it.setTextColor(getColor(android.R.color.darker_gray))
                it.background = null
            }
        }

        tabGallery.setOnClickListener {
            showPanel("gallery")
            setActive(tabGallery, listOf(tabTimeline, tabHeritage))
        }
        tabTimeline.setOnClickListener {
            showPanel("timeline")
            setActive(tabTimeline, listOf(tabGallery, tabHeritage))

            // Find first WIP sculpture for this artist
            val wipSculpture = artist.sculptures.find { it.isWorkInProgress }
            if (wipSculpture != null) {
                tvTimelineTitle.text = "Creation of: ${wipSculpture.title}"
                rvTimeline.layoutManager =
                    androidx.recyclerview.widget.LinearLayoutManager(this)
                rvTimeline.adapter = TimelineAdapter(wipSculpture.wipStages)
            } else {
                tvTimelineTitle.text = "No work in progress for this artist"
            }
        }
        tabHeritage.setOnClickListener {
            showPanel("heritage")
            setActive(tabHeritage, listOf(tabGallery, tabTimeline))
            loadHeritageStory()
        }
    }

    private fun showPanel(panel: String) {
        rvSculptures.visibility = if (panel == "gallery") View.VISIBLE else View.GONE
        panelTimeline.visibility = if (panel == "timeline") View.VISIBLE else View.GONE
        panelHeritage.visibility = if (panel == "heritage") View.VISIBLE else View.GONE
    }

    private fun loadHeritageStory() {
        if (tvHeritageContent.text.isNotEmpty()) return // already loaded

        tvHeritageTitle.text = artist.specialty
        tvHeritageContent.text = ""
        progressHeritage.visibility = View.VISIBLE

        val apiKey = "Bearer ${BuildConfig.GROQ_API_KEY}"// paste your key directly here for now

        val prompt = """
You are a cultural historian specializing in Indian traditional art.
Write a rich 180-word heritage story about "${artist.specialty}" from ${artist.location}, Karnataka, India.
Cover: historical origin, unique carving techniques, cultural significance, and modern relevance.
Simple elegant English. Flowing paragraphs only. No bullet points.
        """.trimIndent()

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    GroqClient.service.getCompletion(
                        auth = apiKey,
                        request = GroqRequest(
                            messages = listOf(
                                GroqMessage(role = "user", content = prompt)
                            )
                        )
                    )
                }
                val story = response.choices.firstOrNull()?.message?.content
                    ?: "Story unavailable."
                tvHeritageContent.text = story

            } catch (e: Exception) {
                tvHeritageContent.text = "Error: ${e.message}"
                Toast.makeText(this@ArtistPortfolioActivity,
                    "Groq error: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                progressHeritage.visibility = View.GONE
            }
        }
    }
}
package com.example.shilpakalashowcase

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)
        supportActionBar?.hide()

        val productId = intent.getStringExtra("SCULPTURE_PRODUCT_ID") ?: ""
        val artistId = intent.getStringExtra("ARTIST_ID") ?: ""

        val artist = SampleData.artists.find { it.id == artistId }
        val sculpture = artist?.sculptures?.find { it.productId == productId }
            ?: SampleData.artists.flatMap { it.sculptures }
                .find { it.productId == productId }!!

        findViewById<TextView>(R.id.tvSculptureTitle).text = sculpture.title
        findViewById<TextView>(R.id.tvProductId).text = "ID: ${sculpture.productId}"
        findViewById<TextView>(R.id.tvMaterial).text = "Material: ${sculpture.material}"
        findViewById<TextView>(R.id.tvDimensions).text = "Size: ${sculpture.dimensions}"
        findViewById<TextView>(R.id.tvPrice).text = sculpture.price

        val photoView = findViewById<PhotoView>(R.id.photoView)
        Glide.with(this).load(sculpture.imageUrl).into(photoView)

        findViewById<android.widget.ImageButton>(R.id.btnBack).setOnClickListener { finish() }

        findViewById<Button>(R.id.btnEnquire).setOnClickListener {
            sendWhatsAppEnquiry(sculpture, artist?.name ?: "")
        }
    }

    private fun sendWhatsAppEnquiry(sculpture: Sculpture, artistName: String) {
        val btn = findViewById<Button>(R.id.btnEnquire)
        btn.text = "Generating message..."
        btn.isEnabled = false

        val prompt = """
Write a short polite WhatsApp message from a buyer enquiring about this sculpture:
- Title: ${sculpture.title}
- Product ID: ${sculpture.productId}
- Material: ${sculpture.material}
- Dimensions: ${sculpture.dimensions}
- Price: ${sculpture.price}
- Artist: $artistName
Rules: Under 6 lines. Include Product ID clearly. Warm professional tone. End with Thank you.
        """.trimIndent()

        val apiKey = "Bearer ${BuildConfig.GROQ_API_KEY}"

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    GroqClient.service.getCompletion(
                        auth = apiKey,
                        request = GroqRequest(
                            messages = listOf(GroqMessage(role = "user", content = prompt)),
                            maxTokens = 200
                        )
                    )
                }
                val message = response.choices.firstOrNull()?.message?.content
                    ?: fallbackMessage(sculpture, artistName)
                openWhatsApp(message)
            } catch (e: Exception) {
                Toast.makeText(this@ImageDetailActivity,
                    "Using standard message", Toast.LENGTH_SHORT).show()
                openWhatsApp(fallbackMessage(sculpture, artistName))
            } finally {
                btn.text = "📲   ENQUIRE ON WHATSAPP"
                btn.isEnabled = true
            }
        }
    }

    private fun fallbackMessage(sculpture: Sculpture, artistName: String): String {
        return """
Hello, I am interested in this sculpture:

🪨 *${sculpture.title}*
🆔 Product ID: ${sculpture.productId}
🎨 Material: ${sculpture.material}
📐 Dimensions: ${sculpture.dimensions}
💰 Price: ${sculpture.price}
👨‍🎨 Artist: $artistName

Please share availability and shipping details. Thank you.
        """.trimIndent()
    }

    private fun openWhatsApp(message: String) {
        val encoded = Uri.encode(message)
        val uri = Uri.parse("https://wa.me/919482554874?text=$encoded")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}

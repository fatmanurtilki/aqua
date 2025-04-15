package com.example.atn

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SesActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ses)

        val sounds = mapOf(
            R.id.btnRain to R.raw.forest,
            R.id.btnFire to R.raw.forest,
            R.id.btnForest to R.raw.forest,
            R.id.btnBrown to R.raw.forest,
            R.id.btnWhite to R.raw.forest,
            R.id.btnLofi to R.raw.forest
        )

        for ((buttonId, soundRes) in sounds) {
            findViewById<Button>(buttonId).setOnClickListener {
                AppPreferences.setString(this, "selected_sound", soundRes.toString())
                playSound(soundRes)
                Toast.makeText(this, "Ses başlatıldı.", Toast.LENGTH_SHORT).show()
            }
        }

        // Önceden seçilmiş sesi yükle
        val savedSound = AppPreferences.getString(this, "selected_sound", "")
        if (savedSound.isNotEmpty()) {
            try {
                playSound(savedSound.toInt())
            } catch (e: Exception) {
                // Ses bozuksa sessiz geç
            }
        }
    }

    private fun playSound(soundResId: Int) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, soundResId)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}

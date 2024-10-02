package com.kiran.moodmusicapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kiran.moodmusicapp.databinding.ActivityHomeScreenBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigate to settings when settings button is clicked
        binding.settingsButtonHome.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        // Mood selection buttons
        binding.happyButton.setOnClickListener { playMusic("Happy") }
        binding.sadButton.setOnClickListener { playMusic("Sad") }
        binding.relaxedButton.setOnClickListener { playMusic("Relaxed") }
        binding.energeticButton.setOnClickListener { playMusic("Energetic") }
        binding.chillButton.setOnClickListener { playMusic("Chill") }
    }

    private fun playMusic(mood: String) {
        val musicIntent = Intent(this, MusicPlayerActivity::class.java)
        musicIntent.putExtra("MOOD", mood)
        startActivity(musicIntent)
    }
}

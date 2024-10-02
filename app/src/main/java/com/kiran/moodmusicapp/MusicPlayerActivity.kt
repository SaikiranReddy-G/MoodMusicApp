package com.kiran.moodmusicapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kiran.moodmusicapp.databinding.ActivityMusicPlayerScreenBinding

class MusicPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicPlayerScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivityMusicPlayerScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mood = intent.getStringExtra("MOOD")

        // Display the mood and load corresponding music
        binding.songTitle.text = "$mood Playlist"

        // Integrate SoundCloud API logic here
        loadMusicFromSoundCloud(mood ?: "Happy")

        binding.playPauseButton.setOnClickListener {
            // Handle play/pause action
        }

        binding.likeButton.setOnClickListener {
            // Handle liking the song
        }

        binding.dislikeButton.setOnClickListener {
            // Handle disliking the song (filter it from appearing again)
        }

        // Back Button to navigate back to Home screen
        binding.backButtonMusicPlayer.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // Close Music Player Activity
        }

        // Settings Button
        binding.settingsButtonMusicPlayer.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadMusicFromSoundCloud(mood: String) {
        // Use SoundCloud API to fetch music based on the mood
        Log.d("MusicPlayer", "Loading music for mood: $mood")
        // Placeholder: Implement the actual API calls here
    }
}

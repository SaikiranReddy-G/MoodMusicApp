package com.kiran.moodmusicapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kiran.moodmusicapp.databinding.ActivityMusicPlayerScreenBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicPlayerScreenBinding
    private var accessToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivityMusicPlayerScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mood = intent.getStringExtra("MOOD")
        binding.songTitle.text = "$mood Playlist"

        // Access token is passed from HomeActivity
        accessToken = intent.getStringExtra("ACCESS_TOKEN")

        if (accessToken != null) {
            loadPlaylist(mood ?: "Happy")
        }

        // Button interactions
        binding.playPauseButton.setOnClickListener {
            // Handle play/pause action
        }
        binding.likeButton.setOnClickListener {
            // Handle liking the song
        }
        binding.dislikeButton.setOnClickListener {
            // Handle disliking the song
        }
        binding.backButtonMusicPlayer.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        binding.settingsButtonMusicPlayer.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    private fun loadPlaylist(mood: String) {
        val client = SpotifyApiClient.getClient()
        val token = "Bearer $accessToken"
        client.searchPlaylists(token, mood).enqueue(object : Callback<PlaylistResponse> {
            override fun onResponse(call: Call<PlaylistResponse>, response: Response<PlaylistResponse>) {
                if (response.isSuccessful) {
                    val playlists = response.body()?.playlists?.items
                    // Handle the retrieved playlists and update UI
                    playlists?.firstOrNull()?.let {
                        binding.songTitle.text = it.name
                    }
                } else {
                    Log.e("MusicPlayer", "Error fetching playlist: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<PlaylistResponse>, t: Throwable) {
                Log.e("MusicPlayer", "API call failed: ${t.message}")
            }
        })
    }
}

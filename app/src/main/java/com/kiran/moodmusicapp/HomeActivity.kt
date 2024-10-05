package com.kiran.moodmusicapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kiran.moodmusicapp.databinding.ActivityHomeScreenBinding
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding

    // Constants for Spotify API
    private val CLIENT_ID = "936c0f95b171439f93fb5a588d1d3259" // Replace with your Spotify Client ID
    private val REDIRECT_URI = "com.kiran.moodmusicapp://callback"
    private val REQUEST_CODE = 1337

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

        // Start Spotify OAuth login
        binding.loginSpotifyButton.setOnClickListener {
            Log.d("HomeActivity", "Login button clicked - Starting Spotify login")
            startSpotifyLogin()
        }

        // Mood selection buttons
        binding.happyButton.setOnClickListener { searchPlaylist("Happy") }
        binding.sadButton.setOnClickListener { searchPlaylist("Sad") }
        binding.relaxedButton.setOnClickListener { searchPlaylist("Relaxed") }
        binding.energeticButton.setOnClickListener { searchPlaylist("Energetic") }
        binding.chillButton.setOnClickListener { searchPlaylist("Chill") }
    }

    private fun startSpotifyLogin() {
        val builder = AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)
        builder.setScopes(arrayOf("playlist-read-private", "playlist-read-collaborative"))
        val request = builder.build()
        AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("HomeActivity", "onActivityResult triggered - Request Code: $requestCode, Result Code: $resultCode")

        if (requestCode == REQUEST_CODE) {
            val response = AuthorizationClient.getResponse(resultCode, data)
            Log.d("HomeActivity", "Spotify Authorization Response: ${response.type}")

            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {
                    val accessToken = response.accessToken
                    Log.d("HomeActivity", "Access Token received: $accessToken")
                    // Save the access token for further API requests
                }
                AuthorizationResponse.Type.ERROR -> {
                    Log.e("HomeActivity", "Authorization error: ${response.error}")
                    // Handle error
                }
                else -> {
                    Log.w("HomeActivity", "Unhandled response type: ${response.type}")
                }
            }
        }
    }

    private fun searchPlaylist(mood: String) {
        val musicIntent = Intent(this, MusicPlayerActivity::class.java)
        musicIntent.putExtra("MOOD", mood)
        startActivity(musicIntent)
    }
}

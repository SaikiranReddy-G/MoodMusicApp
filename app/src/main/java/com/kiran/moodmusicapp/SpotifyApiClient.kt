package com.kiran.moodmusicapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SpotifyApiClient {
    private const val BASE_URL = "https://api.spotify.com/v1/"

    fun getClient(): SpotifyApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpotifyApiService::class.java)
    }
}

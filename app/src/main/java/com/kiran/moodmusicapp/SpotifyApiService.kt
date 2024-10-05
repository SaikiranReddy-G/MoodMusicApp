package com.kiran.moodmusicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SpotifyApiService {
    @GET("search")
    fun searchPlaylists(
        @Header("Authorization") token: String,
        @Query("q") query: String,
        @Query("type") type: String = "playlist"
    ): Call<PlaylistResponse>
}

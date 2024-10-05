package com.kiran.moodmusicapp

data class PlaylistResponse(
    val playlists: Playlists
)

data class Playlists(
    val items: List<PlaylistItem>
)

data class PlaylistItem(
    val id: String,
    val name: String,
    val uri: String,
    val tracks: Tracks
)

data class Tracks(
    val total: Int
)

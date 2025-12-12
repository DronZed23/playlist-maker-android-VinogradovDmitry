package com.example.myapplication.domain

import com.example.myapplication.data.TrackDto

interface IPlaylistRepository {
    val playlists: List<Playlist>
    fun addTrackToPlaylist(playlistName: String, track: TrackDto)
}
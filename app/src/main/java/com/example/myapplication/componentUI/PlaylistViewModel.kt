package com.example.myapplication.componentUI

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.TrackDto
import com.example.myapplication.domain.PlaylistRepository

class PlaylistViewModel : ViewModel() {
    private val repository = PlaylistRepository()

    val playlists = repository.playlists

    fun addTrackToPlaylist(playlistName: String, track: TrackDto) {
        repository.addTrackToPlaylist(playlistName, track)
    }
}
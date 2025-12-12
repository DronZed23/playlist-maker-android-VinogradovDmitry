package com.example.myapplication.domain

import com.example.myapplication.data.TrackDto

class PlaylistRepositoryImpl : IPlaylistRepository {
    private val repository = PlaylistRepository()

    override val playlists: List<Playlist>
        get() = repository.playlists

    override fun addTrackToPlaylist(playlistName: String, track: TrackDto) {
        repository.addTrackToPlaylist(playlistName, track)
    }
}
package com.example.myapplication.domain

import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.data.TrackDto

class PlaylistRepository {
    val playlists = mutableStateListOf<Playlist>()

    init {
        val playlist1 = Playlist("Мои любимые")
        val playlist2 = Playlist("Рок")
        val playlist3 = Playlist("Поп")
        playlists.addAll(listOf(playlist1, playlist2, playlist3))
    }

    fun addTrackToPlaylist(playlistName: String, track: TrackDto) {
        val playlist = playlists.find { it.name == playlistName }
        playlist?.let {
            if (!it.tracks.any { t -> t.trackId == track.trackId }) {
                it.tracks.add(track)
                it.tracksCount.value++
            }
        }
    }
}
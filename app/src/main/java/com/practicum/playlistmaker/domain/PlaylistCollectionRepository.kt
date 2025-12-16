package com.practicum.playlistmaker.domain

import kotlinx.coroutines.flow.Flow

// Интерфейс для работы с коллекцией плейлистов
interface PlaylistCollectionRepository {
    fun fetchAllPlaylists(): Flow<List<Playlist>>
    fun fetchPlaylistById(id: Long): Flow<Playlist?>
    suspend fun createPlaylist(title: String, description: String, coverImageUri: String? = null)
    suspend fun removePlaylist(id: Long)
}
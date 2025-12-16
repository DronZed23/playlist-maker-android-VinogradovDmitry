package com.practicum.playlistmaker.domain

import kotlinx.coroutines.flow.Flow

// Интерфейс для работы с локальным хранилищем треков
interface TrackStorageRepository {
    suspend fun addTrackToPlaylist(track: Track, playlistId: Long)
    suspend fun removeTrackFromPlaylist(trackId: Long, playlistId: Long)
    suspend fun setTrackFavoriteStatus(track: Track, isFavorite: Boolean)
    fun fetchFavoriteTracks(): Flow<List<Track>>
    fun fetchTrackById(trackId: Long): Flow<Track?>
}
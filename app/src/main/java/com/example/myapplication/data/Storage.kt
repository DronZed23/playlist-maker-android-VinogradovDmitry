package com.example.myapplication.data

class Storage {
    private val dummyData = listOf(
        TrackDto(trackId = 5555,"Song A", "Artist A", 210000),
        TrackDto(trackId = 6666,"Song B", "Artist B", 180000),
        TrackDto(trackId = 7777,"Song C", "Artist C", 240000),
        TrackDto(trackId = 8888,"YandexTheme", "GOD", 999999)
    )
    fun findTrackById(trackId: Int): TrackDto? {
        return dummyData.find { it.trackId == trackId }
    }
    fun search(expression: String): List<TrackDto> {
        return dummyData.filter { it.trackName.contains(expression, ignoreCase = true) }
    }
    companion object
}
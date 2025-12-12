package com.example.myapplication.data

import java.util.UUID

data class TrackDto(
    val trackId: Int,
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Int
)
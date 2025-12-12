package com.example.myapplication.domain

import java.util.UUID

data class Track(
    val trackName: String,
    val artistName: String,
    val trackTime: String,
    val trackId: Int
)
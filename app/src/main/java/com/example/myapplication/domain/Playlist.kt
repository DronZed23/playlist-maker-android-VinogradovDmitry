package com.example.myapplication.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.data.TrackDto

data class Playlist(
    val name: String,
    val tracks: MutableList<TrackDto> = mutableListOf(),
    val tracksCount: MutableState<Int> = mutableStateOf(0)
)
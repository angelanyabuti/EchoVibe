package com.example.echovibe.model

data class TodaysMood(
    val id: String,
    val song: String,
    val artist: String,
    val mood: String,
    val date: String,
    val audioUrl: String
)
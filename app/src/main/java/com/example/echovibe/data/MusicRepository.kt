package com.example.echovibe.data

import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun getMusic(): Flow<List<Music>>
}
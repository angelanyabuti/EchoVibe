package com.example.echovibe.data.network

import com.example.echovibe.data.Music
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {
    fun getMusic(): Flow<List<Music>>
}
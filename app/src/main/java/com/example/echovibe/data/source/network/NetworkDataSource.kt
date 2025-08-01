package com.example.echovibe.data.source.network

import com.example.echovibe.data.Music
import kotlinx.coroutines.flow.Flow

/**
 * Main entry point for accessing music data from the network.
 * Defines the functions used to get and set music data.
 */
interface NetworkDataSource {
    fun getMusic(): Flow<List<Music>>
}
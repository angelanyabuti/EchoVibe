package com.example.echovibe.data.source.network

import com.example.echovibe.data.Music
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Receives Firebase Service as a Dependency via Constructor Injection
 * It implements NetworkDataSource interface
 * When someone calls a function, it delegates the call to FirebaseService
 */


class MusicNetworkDataSource @Inject constructor(
    private val firebaseService: FirebaseService
) : NetworkDataSource {

    override  fun getMusic(): Flow<List<Music>> {
        return firebaseService.fetchMusic()
    }

}
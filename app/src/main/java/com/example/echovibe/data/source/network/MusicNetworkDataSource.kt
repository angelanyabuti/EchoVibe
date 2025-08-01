package com.example.echovibe.data.source.network

import com.example.echovibe.data.Music
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MusicNetworkDataSource @Inject constructor(
    private val firebaseService: FirebaseService
) : NetworkDataSource {

    override  fun getMusic(): Flow<List<Music>> {
        return firebaseService.fetchMusic()
    }

}
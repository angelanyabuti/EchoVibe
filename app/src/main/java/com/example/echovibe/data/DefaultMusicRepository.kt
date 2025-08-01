package com.example.echovibe.data

import com.example.echovibe.data.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/***
 * Default implementation of [MusicRepository]. Single entry point for managing the music data
 */

@Singleton
class DefaultMusicRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : MusicRepository {

    override fun getMusic(): Flow<List<Music>> {
        return networkDataSource.getMusic()

    }
}
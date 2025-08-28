package com.example.echovibe.di

import com.example.echovibe.data.DefaultMusicRepository
import com.example.echovibe.data.MusicRepository
import com.example.echovibe.data.source.network.MusicNetworkDataSource
import com.example.echovibe.data.source.network.NetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindTrackRepository(repository: DefaultMusicRepository): MusicRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(repository: MusicNetworkDataSource): NetworkDataSource

}
package com.example.echovibe.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    @ApplicationContext context: Context,
) : ViewModel() {

    private val _exoPlayer = ExoPlayer.Builder(context).build()
    val exoPlayer: ExoPlayer get() = _exoPlayer

    fun preloadTrack(trackUrl: String) {
        val mediaItem = MediaItem.fromUri(trackUrl)
        _exoPlayer.setMediaItem(mediaItem) //preparing the player by adding media items
        _exoPlayer.prepare() //Starts the media loading
    }

    /***
     * Release the player when leaving the screen or closing the app
     * Playback requires resources that might be limited
     * Releasing the player can help prevent the battery from draining and other apps from crashing
     */
    override fun onCleared() {
        super.onCleared()
        _exoPlayer.release()
    }


}
package com.example.echovibe.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.echovibe.data.Music
import com.example.echovibe.data.MusicRepository
import com.example.echovibe.data.source.network.FirebaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the home screen
 */
@HiltViewModel

class MusicViewModel @Inject constructor(

    private val firebaseService: FirebaseService,

    private val musicRepository: MusicRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val _tracks = MutableStateFlow<List<Music>>(emptyList())
    val tracks: StateFlow<List<Music>> = _tracks

    init {
        fetchMusic()
    }

    private fun fetchMusic() {
        viewModelScope.launch {
            firebaseService.fetchMusic().collect { list ->
                _tracks.value = list
            }
        }
    }




}
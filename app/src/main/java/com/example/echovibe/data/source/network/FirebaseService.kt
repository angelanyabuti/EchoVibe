package com.example.echovibe.data.source.network

import com.example.echovibe.data.Music
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/**
 * Firebase service talks directly to firebase firestore
 * */
class FirebaseService {

    private val firestore = FirebaseFirestore.getInstance()

    fun fetchMusic(): Flow<List<Music>> = flow {
        val snapshot = firestore.collection("music").get().await()
        val musicList = snapshot.documents.mapNotNull { it.toObject(Music::class.java) }
        emit(musicList)
    }
}

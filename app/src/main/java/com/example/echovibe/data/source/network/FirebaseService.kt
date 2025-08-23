package com.example.echovibe.data.source.network

import com.example.echovibe.data.Music
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/**
 * Firebase service talks directly to firebase firestore
 * Reads all the documents from the "tracks" collection
 * Converts them into Music objects in the model
 * Exposes them as a flow of lists of Music objects so that you can observe them asynchronously in your app
 * */
class FirebaseService { //creates a firebase instance to give access to firebase

    private val firestore = FirebaseFirestore.getInstance()

    fun fetchMusic(): Flow<List<Music>> = flow {
        val snapshot = firestore.collection("tracks").get().await()
        val musicList = snapshot.documents.mapNotNull { it.toObject(Music::class.java) }
        emit(musicList)
    }
}

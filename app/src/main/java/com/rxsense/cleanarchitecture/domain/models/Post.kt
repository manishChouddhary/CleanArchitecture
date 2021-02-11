package com.rxsense.cleanarchitecture.domain.models

/**
 *
 */
data class Post(
    val ownerName: String,
    val postText: String,
    val publishDate: String,
    val imageUrl: String,
    val likes: Int
)
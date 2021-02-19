package com.jorpl.albumy.models

data class Album (
    val id: Int,
    val title: String,
    val author: String,
    val banner: String,
    val photos: List<String>
)
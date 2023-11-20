package com.example.myapplication.models.getAll

data class Series(
    val available: Long,

    val collectionURI: String,
    val items: List<Item2>,
    val returned: Long,
)
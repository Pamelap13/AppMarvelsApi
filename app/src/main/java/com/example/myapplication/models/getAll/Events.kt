package com.example.myapplication.models.getAll

data class Events(
    val available: Long,
    val collectionURI: String,
    val items: List<Item4>,
    val returned: Long,
)
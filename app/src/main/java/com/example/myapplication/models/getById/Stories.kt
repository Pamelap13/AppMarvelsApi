package com.example.myapplication.models.getById

data class Stories(
    val available: Long,
    val collectionURI: String,
    val items: List<Item3>,
    val returned: Long,
)
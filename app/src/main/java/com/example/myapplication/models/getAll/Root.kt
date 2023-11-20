package com.example.myapplication.models.getAll

data class Root(
    val code: Long,
    val status: String,
    val copyright: String,
    val attributionText: String,

    val attributionHTML: String,
    val etag: String,
    val data: Data,
)
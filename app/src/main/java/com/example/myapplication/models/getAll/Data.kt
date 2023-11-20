package com.example.myapplication.models.getAll

data class Data(
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: List<Result>,
)
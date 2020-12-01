package com.example.smartown.api

data class OutputPost (

    val results: List<Result>
)

data class Result(
    val id: Int,
    val username: String,
    val password: String
)

data class Location(
    val tipo_id: Int,
    val lat: Float,
    val lng: Float,
    val descr: String
)
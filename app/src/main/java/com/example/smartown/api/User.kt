package com.example.smartown.api


data class User(
    val id: Int,
    val username: String,
    val password: String
)

data class Problemas(
    val id: Int,
    val descr: String,
    val userid: Int,
    val geo: Geo
)

data class Geo(
    val lat: String,
    val lng: String
)
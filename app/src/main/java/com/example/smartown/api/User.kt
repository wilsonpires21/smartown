package com.example.smartown.api


data class Users(
    val id: Int,
    val username: String,
    val password: String
)

data class Problem(
    val id: Int,
    val descr: String,
    val user_id: Int,
    val tipo_id: Int,
    val lat: Float,
    val lng: Float
)

data class Tipo(
    val id: Int,
    val descri: String
)
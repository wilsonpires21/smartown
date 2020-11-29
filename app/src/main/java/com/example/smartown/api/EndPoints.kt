package com.example.smartown.api

import com.google.android.gms.maps.LocationSource
import retrofit2.Call
import retrofit2.http.*


interface EndPoints {

    @FormUrlEncoded
    @POST("/api/users/login")
    fun postLogin(@Field("username") username: String,
                  @Field("password") password: String): Call<OutputPost>

    @FormUrlEncoded
    @POST("/api/users/problemas/")
    fun postProb(@Field("tipo_id") tipo_id: Int,
                 @Field("lat") lat: Float,
                 @Field("lng") lng: Float,
                 @Field("descr") descr: String): Call<ResultProb>

    @FormUrlEncoded
    @POST("/api/users/problemas/pontos")
    fun postPontos(): Call<List<ResultProb>>
}



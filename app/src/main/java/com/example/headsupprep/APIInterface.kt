package com.example.headsupprep

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @GET("celebrities/")
    fun getCelebs(): Call<Celebrity>

    @POST("celebrities/")
    fun postCelebs(@Body userData: CelebrityItem): Call<CelebrityItem>

    @PUT("/celebrities/{id}")
    fun updateCelebs(@Path("id") id: Int, @Body userData: CelebrityItem): Call<CelebrityItem>

    @DELETE("/celebrities/{id}")
    fun deleteCelebs(@Path("id") id: Int): Call<Void>
}
package com.example.myapplication

import com.example.myapplication.models.getAll.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterfaces {

    @GET("/v1/public/characters?apikey=5851f6999387039a3ea907434bca6d5c&limit=10")
    fun getAll(@Query("ts") ts:String, @Query("hash") hash:String): Call<Root>

    @GET("/v1/public/characters/{characterId}?apikey=5851f6999387039a3ea907434bca6d5c")
    fun getById(@Path("characterId") characterId:Int,@Query("ts") ts:String, @Query("hash") hash:String):Call<com.example.myapplication.models.getById.Root>
}
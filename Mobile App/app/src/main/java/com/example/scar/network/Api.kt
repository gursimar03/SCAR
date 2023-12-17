package com.example.scar.network

/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import Success
import android.util.Log
import com.example.scar.ui.theme.LeaderboardData
import com.example.scar.ui.theme.MatchData
import com.example.scar.ui.theme.User
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    "https://scarsd3b.online/"

//private const val BASE_URL =
//    "https://demo5970075.mockable.io/"

/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

/**
 * Retrofit service object for creating api calls
 */
interface ApiService{
    @GET("user")
    suspend fun getUsers(): List<User>

    @GET("api/get/testing")
    suspend fun getTests(): Success

    @GET("api/leaderboard")
    suspend fun getPlayers(): LeaderboardData

    @GET("api/get/match_history/1")
    suspend fun getMatches(): MatchData

    @GET("api/get/setup-pubnub")
    fun setupPubnub(): Call<Void>
    @GET("api/get/start-config/1/1")
    fun sendConfig(): Call<Void>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    fun setupPubnub() {
        val call = retrofitService.setupPubnub()

        // Execute the request synchronously (not recommended on the main thread)
        val response = call.execute()

        // Handle the response (check for errors, etc.)
        if (response.isSuccessful) {
            Log.d("Success", response.toString())
        } else {
            Log.d("Fail", response.toString())
        }
    }

    fun sendConfig() {
        val call = retrofitService.sendConfig()

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("Success", response.toString())
                } else {
                    Log.d("Fail", response.toString())
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("Fail", "response.toString()")
            }
        })
    }
}

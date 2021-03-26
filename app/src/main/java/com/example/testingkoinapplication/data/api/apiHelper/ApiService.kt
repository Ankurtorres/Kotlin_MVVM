package com.example.testingkoinapplication.data.api.apiHelper

import com.example.testingkoinapplication.data.api.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers():Response<List<User>>
}
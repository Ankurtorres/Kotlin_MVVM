package com.example.testingkoinapplication.data.api.apiHelper

import com.example.testingkoinapplication.data.api.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers():Response<List<User>>

}
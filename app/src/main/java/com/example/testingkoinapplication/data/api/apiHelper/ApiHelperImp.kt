package com.example.testingkoinapplication.data.api.apiHelper

import com.example.testingkoinapplication.data.api.model.User
import retrofit2.Response

class ApiHelperImp(private val apiService: ApiService): ApiHelper {
    override suspend fun getUsers(): Response<List<User>> =apiService.getUsers();
}
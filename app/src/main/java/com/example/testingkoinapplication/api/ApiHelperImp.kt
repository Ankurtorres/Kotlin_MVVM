package com.example.testingkoinapplication.api

import com.example.testingkoinapplication.model.User
import retrofit2.Response

class ApiHelperImp(private val apiService: ApiService):ApiHelper {
    override suspend fun getUsers(): Response<List<User>> =apiService.getUsers();
}
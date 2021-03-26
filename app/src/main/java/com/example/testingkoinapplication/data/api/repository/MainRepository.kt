package com.example.testingkoinapplication.data.api.repository

import com.example.testingkoinapplication.data.api.apiHelper.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers()=apiHelper.getUsers()
}
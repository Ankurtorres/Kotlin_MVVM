package com.example.testingkoinapplication.repository

import com.example.testingkoinapplication.api.ApiHelper

class MainRepository(private val apiHeper: ApiHelper) {
    suspend fun getUsers()=apiHeper.getUsers()
}
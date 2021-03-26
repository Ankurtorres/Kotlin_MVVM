package com.example.testingkoinapplication.di.module

import com.example.testingkoinapplication.data.api.repository.MainRepository
import org.koin.dsl.module

val repoModule= module {
    single {
        MainRepository(get())
    }
}
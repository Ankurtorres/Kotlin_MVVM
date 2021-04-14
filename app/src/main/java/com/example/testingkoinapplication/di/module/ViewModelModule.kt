package com.example.testingkoinapplication.di.module

import com.example.testingkoinapplication.ui.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel {
        MainViewModel(mainRepository=get(),networkHelper=get())
    }
}
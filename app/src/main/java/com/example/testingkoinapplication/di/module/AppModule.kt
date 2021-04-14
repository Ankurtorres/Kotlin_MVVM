package com.example.testingkoinapplication.di.module

import android.content.Context
import com.example.testingkoinapplication.data.api.apiHelper.ApiHelper
import com.example.testingkoinapplication.data.api.apiHelper.ApiHelperImp
import com.example.testingkoinapplication.data.api.apiHelper.ApiService
import com.example.testingkoinapplication.utils.NetworkHelper
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.itkacher.okprofiler.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(okHttpClient=get(), com.example.testingkoinapplication.BuildConfig.BASE_URL) }
    single { provideApiService(retrofit=get()) }
    single { provideNetworkHelper(androidContext()) }

    single<ApiHelper> { ApiHelperImp(apiService=get()) }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() = /*if (BuildConfig.DEBUG) {*/
   /* val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)*/
    OkHttpClient.Builder()
       /* .addInterceptor(loggingInterceptor)*/
        .addInterceptor(OkHttpProfilerInterceptor())
        .build()
/*} else OkHttpClient
    .Builder()
    .build()*/

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

private fun provideApiHelper(apiHelper: ApiHelperImp): ApiHelper = apiHelper
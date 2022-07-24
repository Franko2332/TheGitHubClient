package ru.gb.thegithubclient.customdi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.dil.Di
import ru.gb.thegithubclient.data.concreterepo.RetrofitRepoImpl
import ru.gb.thegithubclient.data.retrofit.GitHubApi
import ru.gb.thegithubclient.domain.repo.Repo

class DiModule {
    private val baseUrl = "https://api.github.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }.build())
            .build()
    }


    private val gitHubApi: GitHubApi by lazy { retrofit.create(GitHubApi::class.java) }

    private val retrofitRepoImpl: Repo by lazy { RetrofitRepoImpl(gitHubApi) }

    init {
        Di.add(retrofitRepoImpl)
    }
}
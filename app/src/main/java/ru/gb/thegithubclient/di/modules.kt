package ru.gb.thegithubclient.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.thegithubclient.data.concreterepo.RetrofitRepoImpl
import ru.gb.thegithubclient.data.retrofit.GitHubApi
import ru.gb.thegithubclient.domain.repo.Repo

val appModule = module {
    val baseUrl = "https://api.github.com/"
    single<Repo>{RetrofitRepoImpl(get())}
    single<String> {"https://api.github.com/"}
    single<GitHubApi> {get<Retrofit>().create(GitHubApi::class.java)}
    single{Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build())
        .build()}
}
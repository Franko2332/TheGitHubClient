package ru.gb.thegithubclient.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.thegithubclient.data.concreterepo.RetrofitRepoImpl
import ru.gb.thegithubclient.data.retrofit.GitHubApi
import ru.gb.thegithubclient.domain.repo.Repo
import javax.inject.Singleton

@Module
class DaggerModule {
    private val baseUrl = "https://api.github.com/"

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit{
        return  Retrofit.Builder()
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
    @Provides
    @Singleton
    fun getGitHubApi(retrofit: Retrofit): GitHubApi = retrofit.create(GitHubApi::class.java)

    @Provides
    @Singleton
    fun getRetrofitRepoImpl(api: GitHubApi):Repo = RetrofitRepoImpl(api)
}
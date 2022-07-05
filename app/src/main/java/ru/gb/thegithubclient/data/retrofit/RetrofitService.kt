package ru.gb.thegithubclient.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

object RetrofitService {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://api.github.com/"

    public fun getGitHubApiService(): GitHubApi {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(OkHttpClient.Builder().apply {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }.build())
                .build()
        }
        return retrofit!!.create(GitHubApi::class.java)
    }
}

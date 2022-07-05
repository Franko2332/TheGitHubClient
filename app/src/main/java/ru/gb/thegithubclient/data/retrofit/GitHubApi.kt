package ru.gb.thegithubclient.data.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import ru.gb.thegithubclient.BuildConfig
import ru.gb.thegithubclient.domain.entity.UserEntity

interface GitHubApi {
   @GET("users")
    suspend fun getUsers(
       @Header("Authorization")
       accessToken: String = BuildConfig.GITHUB_TOKEN_KEY): List<UserEntity>

    @GET("users")
    fun getObservableUsers(
        @Header("Authorization")
        accessToken: String = BuildConfig.GITHUB_TOKEN_KEY): Single<List<UserEntity>>
}
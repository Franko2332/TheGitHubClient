package ru.gb.thegithubclient.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Header
import ru.gb.thegithubclient.BuildConfig
import ru.gb.thegithubclient.data.pojo.UserEntity

interface GitHubApi {
   @GET("users")
    suspend fun getUsers(
       @Header("Authorization")
       accessToken: String = BuildConfig.GITHUB_TOKEN_KEY): List<UserEntity>
}
package ru.gb.thegithubclient.data.pojo

import com.google.gson.annotations.SerializedName

data class UserEntity(@SerializedName("login") val login: String?,
                      @SerializedName("avatar_url") val avatarUrl: String?,
                      @SerializedName("id") var userId: Int)

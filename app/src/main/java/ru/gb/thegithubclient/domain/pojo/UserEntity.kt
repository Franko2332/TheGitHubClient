package ru.gb.thegithubclient.domain.pojo

import android.util.Log
import com.google.gson.annotations.SerializedName
import ru.gb.thegithubclient.R

data class UserEntity(@SerializedName("login") val login: String?,
                      @SerializedName("avatar_url") val avatarUrl: String?,
                      @SerializedName("id") var userId: Int)

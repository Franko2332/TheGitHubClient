package ru.gb.thegithubclient.domain.pojo

import com.google.gson.annotations.SerializedName
import ru.gb.thegithubclient.R

data class UserEntity(@SerializedName("login") val login: String?,
                      @SerializedName("avatar_url") val avatarUrl: String?,
                      @SerializedName("id") val userId: Int): BindableEntity {
    override val layoutRes = R.layout.item_user
    override val viewType = 1
                      }
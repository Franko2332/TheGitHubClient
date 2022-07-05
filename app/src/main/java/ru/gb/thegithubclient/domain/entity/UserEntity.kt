package ru.gb.thegithubclient.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    @SerializedName("login") val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("id") var userId: Int
) : Parcelable

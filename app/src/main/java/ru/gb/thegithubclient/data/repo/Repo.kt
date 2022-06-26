package ru.gb.thegithubclient.data.repo

import ru.gb.thegithubclient.domain.pojo.UserEntity

interface Repo {
    suspend fun getUsersData(): List<UserEntity>
}

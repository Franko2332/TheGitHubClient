package ru.gb.thegithubclient.domain.repo

import ru.gb.thegithubclient.data.pojo.UserEntity

interface Repo {
    suspend fun getUsersData(): List<UserEntity>
}

package ru.gb.thegithubclient.domain.repo

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.gb.thegithubclient.domain.entity.UserEntity

interface Repo {
    suspend fun getUsersData(): List<UserEntity>
    fun getObservableUsersData(): Single<List<UserEntity>>
}

package ru.gb.thegithubclient.data.concreterepo

import io.reactivex.rxjava3.core.Single
import ru.gb.thegithubclient.data.retrofit.GitHubApi
import ru.gb.thegithubclient.domain.entity.UserEntity
import ru.gb.thegithubclient.domain.repo.Repo

class RetrofitRepoImpl( private val gitHubApi: GitHubApi) : Repo {
    override suspend fun getUsersData(): List<UserEntity> = gitHubApi.getUsers()

    override fun getObservableUsersData(): Single<List<UserEntity>> = gitHubApi.getObservableUsers()
}
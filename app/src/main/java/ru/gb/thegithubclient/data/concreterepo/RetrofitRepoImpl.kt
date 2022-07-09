package ru.gb.thegithubclient.data.concreterepo

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.gb.thegithubclient.domain.entity.UserEntity
import ru.gb.thegithubclient.data.retrofit.RetrofitService
import ru.gb.thegithubclient.domain.repo.Repo

class RetrofitRepoImpl : Repo {
    override suspend fun getUsersData():
            List<UserEntity> = RetrofitService.getGitHubApiService().getUsers()

    override fun getObservableUsersData(): Single<List<UserEntity>> =
        RetrofitService.getGitHubApiService().getObservableUsers()
}
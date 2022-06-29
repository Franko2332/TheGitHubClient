package ru.gb.thegithubclient.data

import ru.gb.thegithubclient.data.pojo.UserEntity
import ru.gb.thegithubclient.data.retrofit.RetrofitService
import ru.gb.thegithubclient.domain.repo.Repo

class RepoImpl: Repo {
    override suspend fun getUsersData():
            List<UserEntity> = RetrofitService.getGitHubApiService().getUsers()
}
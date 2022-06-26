package ru.gb.thegithubclient.data.repo

import ru.gb.thegithubclient.domain.pojo.UserEntity
import ru.gb.thegithubclient.data.retrofit.RetrofitService

class RepoImpl: Repo {
    override suspend fun getUsersData():
            List<UserEntity> = RetrofitService.getGitHubApiService().getUsers()
}
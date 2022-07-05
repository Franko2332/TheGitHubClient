package ru.gb.thegithubclient

import android.app.Application
import android.content.Context
import ru.gb.thegithubclient.data.concreterepo.RetrofitRepoImpl
import ru.gb.thegithubclient.domain.repo.Repo

class App: Application() {
    val usersRepo: Repo by lazy { RetrofitRepoImpl() }
}

val Context.app: App get() = applicationContext as App
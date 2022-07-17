package ru.gb.thegithubclient.di

import dagger.Component

import ru.gb.thegithubclient.data.concreterepo.RetrofitRepoImpl
import ru.gb.thegithubclient.ui.users.MainActivity
import javax.inject.Singleton


@Component(modules = [DaggerModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}



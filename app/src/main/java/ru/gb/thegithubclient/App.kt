package ru.gb.thegithubclient

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import ru.gb.thegithubclient.customdi.DiModule
import ru.gb.thegithubclient.di.DaggerAppComponent
import ru.gb.thegithubclient.di.appModule

class App : Application(){
    val diModule: DiModule = DiModule()
}

val Context.app: App get() = applicationContext as App
package ru.gb.thegithubclient.ui.users

import android.opengl.Visibility
import ru.gb.thegithubclient.data.pojo.BindableModel

interface UsersContract {
    interface View{
       fun showProgress(visibility: Boolean)
       fun showUsers(data: List<BindableModel>)
       fun showError(error: Throwable)

    }

    interface Presenter{
        fun detach()
        fun attach(view: UsersContract.View)
        suspend fun loadData()

    }
}
package ru.gb.thegithubclient.ui.users

import ru.gb.thegithubclient.ui.BindableModel

interface UsersContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showUsers(data: List<BindableModel>)
        fun showError(error: Throwable)

    }

    interface Presenter {
        fun detach()
        fun attach(view: UsersContract.View)
        suspend fun loadData()

    }
}
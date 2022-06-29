package ru.gb.thegithubclient.ui.users

import ru.gb.thegithubclient.data.pojo.BindableModel
import ru.gb.thegithubclient.data.pojo.UserBindableEntity
import ru.gb.thegithubclient.domain.repo.Repo
import java.lang.IllegalStateException

class UsersPresenter(private val repo: Repo) : UsersContract.Presenter {
    private var view: UsersContract.View? = null
    override fun detach() {
        view = null
    }

    override fun attach(view: UsersContract.View) {
        this.view = view
    }

    override suspend fun loadData() {
        try {
            view?.showProgress(true)
            val users = repo.getUsersData()
            val data = mutableListOf<BindableModel>()
            users.forEach {
                data.add(UserBindableEntity(it))
            }
            view?.showUsers(data)
            view?.showProgress(false)
        } catch (e: Throwable) {
            view?.showError(e)
        }
    }

}

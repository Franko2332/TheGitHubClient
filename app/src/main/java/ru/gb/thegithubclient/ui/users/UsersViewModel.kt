package ru.gb.thegithubclient.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.gb.thegithubclient.data.RepoImpl
import ru.gb.thegithubclient.data.UsersAppState
import ru.gb.thegithubclient.data.pojo.BindableModel
import ru.gb.thegithubclient.data.pojo.UserBindableEntity
import ru.gb.thegithubclient.data.pojo.UserEntity
import ru.gb.thegithubclient.domain.repo.Repo
import java.lang.IllegalStateException

class UsersViewModel(private var repo: Repo) {
    private val usersData: LiveData<UsersAppState> = MutableLiveData<UsersAppState>()

    suspend fun getUsersData(): LiveData<UsersAppState> {
        usersData.mutable().postValue(UsersAppState.Loading)
        try {
            val usersEntity = repo.getUsersData()
            usersData.mutable().postValue(UsersAppState.Success(bindData(usersEntity)))
        } catch (e: Throwable){
            usersData.mutable().postValue(UsersAppState.Error(e))
        }
        return usersData
    }

    private fun bindData(usersEntity: List<UserEntity>): MutableList<UserBindableEntity> {
        val data = mutableListOf<UserBindableEntity>()
        usersEntity.forEach {
            data.add(UserBindableEntity((it)))
        }
        return data
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> = this as? MutableLiveData<T>
        ?: throw IllegalStateException("It is not mutableLiveData")
}
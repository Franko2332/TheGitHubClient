package ru.gb.thegithubclient.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import ru.gb.thegithubclient.domain.appstate.UsersAppState
import ru.gb.thegithubclient.domain.entity.UserEntity
import ru.gb.thegithubclient.domain.repo.Repo
import java.lang.IllegalStateException

class UsersViewModel(private var repo: Repo) {
    private val usersData: Observable<UsersAppState> = BehaviorSubject.create()

    fun getUsersData(): Observable<UsersAppState> {
        usersData.subject().onNext(UsersAppState.Loading)
        repo.getObservableUsersData().subscribeBy(
            onError = { usersData.subject().onNext(UsersAppState.Error(it)) },
            onSuccess = { usersData.subject().onNext(UsersAppState.Success(bindData(it))) })
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

    private fun <T> Observable<T>.subject(): Subject<T> = this as? Subject<T>
        ?: throw IllegalStateException("It is not Subject")
}
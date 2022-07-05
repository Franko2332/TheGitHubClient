package ru.gb.thegithubclient.domain.appstate

import ru.gb.thegithubclient.ui.users.UserBindableEntity

sealed class UsersAppState {
     object Loading: UsersAppState()
     data class Success(val data: List<UserBindableEntity>): UsersAppState()
     data class Error(val error: Throwable): UsersAppState()
}
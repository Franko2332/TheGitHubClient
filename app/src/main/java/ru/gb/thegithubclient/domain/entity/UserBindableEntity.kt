package ru.gb.thegithubclient.domain.entity

import ru.gb.thegithubclient.R
import ru.gb.thegithubclient.ui.BindableModel
import ru.gb.thegithubclient.domain.entity.UserEntity

class UserBindableEntity(val _data: UserEntity): BindableModel {
    override var layoutRes: Int = R.layout.item_user
    override val data: Any
        get() = _data as UserEntity
    override val viewType: Int = 1
}
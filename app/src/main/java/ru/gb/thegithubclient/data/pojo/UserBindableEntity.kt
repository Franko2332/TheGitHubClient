package ru.gb.thegithubclient.data.pojo

import ru.gb.thegithubclient.R

class UserBindableEntity(val _data: UserEntity):BindableModel {
    override var layoutRes: Int = R.layout.item_user
    override val data: Any
        get() = _data as UserEntity
    override val viewType: Int = 1
}
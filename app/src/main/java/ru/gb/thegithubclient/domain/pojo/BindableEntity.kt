package ru.gb.thegithubclient.domain.pojo

import androidx.annotation.LayoutRes

interface BindableEntity {
    @get: LayoutRes
    val layoutRes: Int
    val viewType: Int
        get() = 0
}
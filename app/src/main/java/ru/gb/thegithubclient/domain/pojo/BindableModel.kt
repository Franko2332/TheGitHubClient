package ru.gb.thegithubclient.domain.pojo

import androidx.annotation.LayoutRes

interface BindableModel {
    @get: LayoutRes
    var layoutRes: Int
    val viewType: Int
        get() = 0
    val data: Any
}
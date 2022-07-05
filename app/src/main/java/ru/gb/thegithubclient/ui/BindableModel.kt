package ru.gb.thegithubclient.ui

import androidx.annotation.LayoutRes

interface BindableModel {
    @get: LayoutRes
    var layoutRes: Int
    val viewType: Int
        get() = 0
    val data: Any
}
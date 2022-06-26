package ru.gb.thegithubclient.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.thegithubclient.R
import ru.gb.thegithubclient.domain.pojo.BindableModel

open class EntityHolder(parent: ViewGroup, layoutId: Int) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {
    open fun bind(entity: BindableModel){

    }
}
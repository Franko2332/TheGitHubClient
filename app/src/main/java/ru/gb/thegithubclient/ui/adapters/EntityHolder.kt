package ru.gb.thegithubclient.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.thegithubclient.domain.pojo.BindableEntity

open class EntityHolder(parent: ViewGroup, layoutId: Int) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutId, parent)) {
    open fun bind(entity: BindableEntity){

    }
}
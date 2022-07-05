package ru.gb.thegithubclient.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.thegithubclient.ui.BindableModel

abstract class EntityHolder(parent: ViewGroup, layoutId: Int) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {
    abstract fun bind(entity: BindableModel)
    abstract fun setOnItemClickListener(listener: Adapter.OnItemClickListener)
}
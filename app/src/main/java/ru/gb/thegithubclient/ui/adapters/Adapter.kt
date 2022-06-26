package ru.gb.thegithubclient.ui.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.thegithubclient.domain.pojo.BindableEntity

class Adapter <T: EntityHolder>: RecyclerView.Adapter<T>() {
    private val data = mutableListOf<BindableEntity>()
    private val viewTypeToLayoutId :MutableMap<Int, Int> = mutableMapOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(_data: List<BindableEntity>){
        data.clear()
        data.addAll(_data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T
    = EntityHolder(parent, viewTypeToLayoutId[viewType]!!) as T

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemViewType(position: Int): Int {
        val item = data[position]
        if(!viewTypeToLayoutId.containsKey(item.viewType)){
            viewTypeToLayoutId[item.viewType]= item.layoutRes
        }
        return item.viewType
    }

    override fun getItemCount(): Int = data.size
}
package ru.gb.thegithubclient.ui.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.thegithubclient.data.pojo.BindableModel
import ru.gb.thegithubclient.data.pojo.UserEntity

class Adapter <T: EntityHolder>: RecyclerView.Adapter<T>() {
    private val data = mutableListOf<BindableModel>()
    private var viewTypeToLayoutId :MutableMap<Int, Int> = mutableMapOf()
    private var listener: OnItemClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(_data: List<BindableModel>){
        data.clear()
        data.addAll(_data)
        notifyDataSetChanged()
    }

    fun setOnclickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T = GitHubUsersHolder(parent, viewTypeToLayoutId[viewType]!!) as T

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bind(data[position])
        listener?.let { holder.setOnItemClickListener(it) }
    }

    override fun getItemViewType(position: Int): Int {
        val item = data[position]
        if(!viewTypeToLayoutId.containsKey(item.viewType)){
            viewTypeToLayoutId[item.viewType]= item.layoutRes
        }
        return item.viewType
    }

    override fun getItemCount(): Int = data.size

    interface OnItemClickListener{
        fun onClick(userEntity: UserEntity)
    }
}
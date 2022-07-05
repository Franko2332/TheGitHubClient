package ru.gb.thegithubclient.ui.users


import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

import com.squareup.picasso.Picasso
import ru.gb.thegithubclient.R
import ru.gb.thegithubclient.ui.BindableModel
import ru.gb.thegithubclient.domain.entity.UserEntity
import ru.gb.thegithubclient.ui.adapters.Adapter
import ru.gb.thegithubclient.ui.adapters.EntityHolder

class GitHubUsersHolder(parent: ViewGroup, layoutRes: Int) : EntityHolder(parent, layoutRes) {
    var data: UserEntity? = null
    override fun bind(entity: BindableModel) {
       data = entity.data as UserEntity
        val im = itemView.findViewById<ImageView>(R.id.user_image_view)
        Picasso.get().load(data?.avatarUrl).into(im)
        val tv = itemView.findViewById<TextView>(R.id.user_login_text_view)
        tv.text = data?.login.toString()
    }

    override fun setOnItemClickListener(listener: Adapter.OnItemClickListener) {
        itemView.findViewById<CardView>(R.id.holder_card_view)
            .setOnClickListener { data?.let { listener.onClick(it) } }
    }
}
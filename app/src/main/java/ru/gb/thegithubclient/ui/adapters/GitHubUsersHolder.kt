package ru.gb.thegithubclient.ui.adapters


import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso
import ru.gb.thegithubclient.R
import ru.gb.thegithubclient.data.pojo.BindableModel
import ru.gb.thegithubclient.data.pojo.UserEntity

class GitHubUsersHolder(parent: ViewGroup, layoutRes: Int): EntityHolder(parent, layoutRes) {
    override fun bind(entity: BindableModel) {
        val data = entity.data as UserEntity
        val im = itemView.findViewById<ImageView>(R.id.user_image_view)
            Picasso.get().load(data.avatarUrl).into(im)
        val tv = itemView.findViewById<TextView>(R.id.user_login_text_view)
        tv.text = data.login.toString()
        }
    }
package ru.gb.thegithubclient.ui.adapters

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ru.gb.thegithubclient.R
import ru.gb.thegithubclient.domain.pojo.BindableEntity
import ru.gb.thegithubclient.domain.pojo.UserEntity

class GitHubUsersHolder(parent: ViewGroup, layoutRes: Int): EntityHolder(parent, layoutRes) {
    override fun bind(entity: BindableEntity) {
        entity as UserEntity
        val im = itemView.findViewById<ImageView>(R.id.user_image_view).apply {
            Picasso.get().load(entity.avatarUrl).into(this)
        }
        val tv = itemView.findViewById<TextView>(R.id.user_login_text_view).apply {
            text = entity.login
        }
    }
}
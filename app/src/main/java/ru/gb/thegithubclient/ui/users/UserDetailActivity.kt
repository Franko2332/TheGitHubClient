package ru.gb.thegithubclient.ui.users

import android.app.Activity
import android.os.Bundle
import com.squareup.picasso.Picasso
import ru.gb.thegithubclient.domain.entity.UserEntity
import ru.gb.thegithubclient.databinding.ActivityUserDetailBinding

class UserDetailActivity: Activity() {
    private val binding: ActivityUserDetailBinding by lazy { ActivityUserDetailBinding.inflate(layoutInflater) }

    companion object {
        private const val USER_ENTITY = "USER_ENTITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intent.getParcelableExtra<UserEntity>(USER_ENTITY)?.let { showUserInfo(it) }

    }

    private fun showUserInfo(userEntity: UserEntity){
        Picasso.get().load(userEntity.avatarUrl).into(binding.userDetailImageView)
        binding.userLogin.text = userEntity.login
    }


}
package ru.gb.thegithubclient.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RestrictTo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.gb.thegithubclient.R
import ru.gb.thegithubclient.data.repo.Repo
import ru.gb.thegithubclient.data.repo.RepoImpl
import ru.gb.thegithubclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var success: Boolean = false
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val repo: Repo = RepoImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        lifecycle.coroutineScope.launchWhenStarted {
            try {
              val data = repo.getUsersData()
                Log.e("data", data.toString())

            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }

    }

    private fun changeProgressBarVisibility(visibility: Boolean) {
        if (visibility) {
            binding.usersRecyclerView.visibility = View.GONE
            binding.progressBarUsersContainer.visibility = View.VISIBLE
        } else {
            binding.usersRecyclerView.visibility = View.VISIBLE
            binding.progressBarUsersContainer.visibility = View.GONE
        }
    }
}
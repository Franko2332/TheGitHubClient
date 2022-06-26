package ru.gb.thegithubclient.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.thegithubclient.data.repo.Repo
import ru.gb.thegithubclient.data.repo.RepoImpl
import ru.gb.thegithubclient.databinding.ActivityMainBinding
import ru.gb.thegithubclient.domain.pojo.BindableModel
import ru.gb.thegithubclient.domain.pojo.UserBindableEntity
import ru.gb.thegithubclient.ui.adapters.Adapter
import ru.gb.thegithubclient.ui.adapters.GitHubUsersHolder

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val repo: Repo = RepoImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        changeProgressBarVisibility(true)
        lifecycle.coroutineScope.launchWhenStarted {
            try {
                val users = repo.getUsersData()
                binding.usersRecyclerView.apply {
                    adapter = Adapter<GitHubUsersHolder>().apply {
                        val data = mutableListOf<BindableModel>()
                        users.forEach {
                            data.add(UserBindableEntity(it))
                        }
                        setData(data)
                    }
                    layoutManager = LinearLayoutManager(
                        baseContext,
                        LinearLayoutManager.VERTICAL, false
                    )
                }
                changeProgressBarVisibility(false)


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
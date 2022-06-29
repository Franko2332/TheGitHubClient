package ru.gb.thegithubclient.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.thegithubclient.domain.repo.Repo
import ru.gb.thegithubclient.data.RepoImpl
import ru.gb.thegithubclient.databinding.ActivityMainBinding
import ru.gb.thegithubclient.data.pojo.BindableModel
import ru.gb.thegithubclient.data.pojo.UserBindableEntity
import ru.gb.thegithubclient.ui.adapters.Adapter
import ru.gb.thegithubclient.ui.adapters.GitHubUsersHolder
import ru.gb.thegithubclient.ui.users.UsersContract
import ru.gb.thegithubclient.ui.users.UsersPresenter
import java.time.Duration

class MainActivity : AppCompatActivity(), UsersContract.View {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var presenter: UsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = extractPresenter()
        presenter.attach(this)
        init()
    }

    private fun extractPresenter(): UsersContract.Presenter =
        lastCustomNonConfigurationInstance as? UsersContract.Presenter
            ?: UsersPresenter(RepoImpl())

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun init() {
        showProgress(true)
        lifecycle.coroutineScope.launchWhenStarted {
            presenter.loadData()
        }

    }

    override fun onRetainCustomNonConfigurationInstance(): UsersContract.Presenter? {
        return presenter
    }

    override fun showUsers(data: List<BindableModel>) {
        binding.usersRecyclerView.apply {
            adapter = Adapter<GitHubUsersHolder>().apply {
                setData(data)
            }
            layoutManager = LinearLayoutManager(
                baseContext,
                LinearLayoutManager.VERTICAL, false
            )
        }
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(visibility: Boolean) {
        if (visibility) {
            binding.usersRecyclerView.visibility = View.GONE
            binding.progressBarUsersContainer.visibility = View.VISIBLE
        } else {
            binding.usersRecyclerView.visibility = View.VISIBLE
            binding.progressBarUsersContainer.visibility = View.GONE
        }
    }
}
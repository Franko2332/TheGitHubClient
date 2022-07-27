package ru.gb.thegithubclient.ui.users

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.gb.dil.Di
import ru.gb.thegithubclient.domain.appstate.UsersAppState
import ru.gb.thegithubclient.domain.appstate.UsersAppState.*
import ru.gb.thegithubclient.databinding.ActivityMainBinding
import ru.gb.thegithubclient.ui.BindableModel
import ru.gb.thegithubclient.domain.entity.UserEntity
import ru.gb.thegithubclient.service.CustomIntentService
import ru.gb.thegithubclient.ui.Adapter

class MainActivity : AppCompatActivity(), UsersContract.View, Adapter.OnItemClickListener {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: UsersViewModel
    private lateinit var disposable: Disposable
    private lateinit var rxButtonDisposable: Disposable
    private lateinit var intent_: Intent


    companion object {
        private const val USER_ENTITY = "USER_ENTITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = extractViewModel()
        init()

    }

    override fun onDestroy() {
        disposable.dispose()
        rxButtonDisposable.dispose()
        stopService(intent_)
        super.onDestroy()
    }

    private fun extractViewModel(): UsersViewModel =
        lastCustomNonConfigurationInstance as? UsersViewModel
            ?: UsersViewModel(Di.get())

    private fun init() {
        val rxFab = binding.fab
        disposable = viewModel.getUsersData().observeOn(AndroidSchedulers.mainThread()).subscribe {
            render(it)
        }
        rxButtonDisposable =rxFab.clickEventObservable.subscribeBy(
            onError = { showError(it) },
            onNext = { viewModel.getUsersData() })
        intent_ = Intent(this, CustomIntentService::class.java).also {
            startService(it)
        }
    }

    override fun onRetainCustomNonConfigurationInstance(): UsersViewModel? {
        return this.viewModel
    }

    private fun render(appState: UsersAppState) = when (appState) {
        is Loading -> showProgress()
        is Success -> showUsers(appState.data)
        is Error -> showError(appState.error)
    }

    override fun showUsers(data: List<BindableModel>) {
        hideProgress()
        binding.usersRecyclerView.apply {
            adapter = Adapter<GitHubUsersHolder>().apply {
                setData(data)
                setOnclickListener(this@MainActivity)
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

    override fun showProgress() {
        binding.progressBarUsersContainer.visibility = View.VISIBLE
        binding.usersRecyclerView.visibility = View.GONE
    }

    override fun hideProgress() {
        binding.progressBarUsersContainer.visibility = View.GONE
        binding.usersRecyclerView.visibility = View.VISIBLE
    }

    override fun onClick(userEntity: UserEntity) {
        val intent = Intent(this, UserDetailActivity::class.java).apply {
            val bundle = Bundle().apply {
                putParcelable(USER_ENTITY, userEntity)
            }
            putExtras(bundle)
        }
        startActivity(intent)
    }


}
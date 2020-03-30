package tim.githubusers.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import tim.githubusers.api.SchedulerProvider
import tim.githubusers.models.GithubRepository
import tim.githubusers.models.User
import tim.githubusers.ui.base.BaseViewModel

class HomeViewModel(
    repo: GithubRepository,
    scheduler: SchedulerProvider
) : BaseViewModel() {

    val usersList: LiveData<PagedList<User>>
    val throwableEvent = MutableLiveData<Throwable>()
    private val pageSize = 15

    private val dataSourceFactory: UserDataSourceFactory =
        UserDataSourceFactory(disposables, repo, scheduler, throwableEvent)

    init {

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()

        usersList = LivePagedListBuilder<Long, User>(dataSourceFactory, config).build()

    }
}
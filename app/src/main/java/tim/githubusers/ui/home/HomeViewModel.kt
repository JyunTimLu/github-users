package tim.githubusers.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import tim.githubusers.models.GithubRepository
import tim.githubusers.models.User

class HomeViewModel(
    repo: GithubRepository
) : ViewModel() {

    val usersList: LiveData<PagedList<User>>
    val throwableEvent = MutableLiveData<Throwable>()
    private val pageSize = 15

    private val dataSourceFactory: UserDataSourceFactory =
        UserDataSourceFactory(repo, throwableEvent, viewModelScope)

    init {

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()

        usersList = LivePagedListBuilder<Long, User>(dataSourceFactory, config).build()

    }
}
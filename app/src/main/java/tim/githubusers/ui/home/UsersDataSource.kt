package tim.githubusers.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import tim.githubusers.models.GithubRepository
import tim.githubusers.models.User
import kotlin.Exception

class UsersDataSource(
    private val githubRepository: GithubRepository,
    private val throwableEvent: MutableLiveData<Throwable>,
    private val coroutineScope: CoroutineScope
) : ItemKeyedDataSource<Long, User>() {

    private val exceptionHandler = CoroutineExceptionHandler { _, e -> throwableEvent.postValue(e) }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<User>) {
        coroutineScope.launch(exceptionHandler) {
            val users = githubRepository.getUsers(1, params.requestedLoadSize)
            callback.onResult(users)
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<User>) {
        coroutineScope.launch(exceptionHandler) {
            val users = githubRepository.getUsers(params.key, params.requestedLoadSize)
            callback.onResult(users)
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<User>) {

    }

    override fun getKey(item: User): Long {
        return item.id
    }
}
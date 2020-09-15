package tim.githubusers.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import tim.githubusers.models.GithubRepository
import tim.githubusers.models.User

class UserDataSourceFactory(
    private val githubRepository: GithubRepository,
    private val throwableEvent: MutableLiveData<Throwable>,
    private val coroutineScope: CoroutineScope
) : DataSource.Factory<Long, User>() {

    private val usersDataSourceLiveData = MutableLiveData<UsersDataSource>()

    override fun create(): DataSource<Long, User> {
        val usersDataSource = UsersDataSource(
            githubRepository = githubRepository,
            throwableEvent = throwableEvent,
            coroutineScope = coroutineScope
        )
        usersDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }

}
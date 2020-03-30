package tim.githubusers.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import tim.githubusers.api.SchedulerProvider
import tim.githubusers.models.GithubRepository
import tim.githubusers.models.User

class UserDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val githubRepository: GithubRepository,
    private val scheduler: SchedulerProvider,
    private val throwableEvent: MutableLiveData<Throwable>
) : DataSource.Factory<Long, User>() {

    private val usersDataSourceLiveData = MutableLiveData<UsersDataSource>()

    override fun create(): DataSource<Long, User> {
        val usersDataSource = UsersDataSource(
            compositeDisposable = compositeDisposable,
            githubRepository = githubRepository,
            scheduler = scheduler,
            throwableEvent = throwableEvent
        )
        usersDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }

}
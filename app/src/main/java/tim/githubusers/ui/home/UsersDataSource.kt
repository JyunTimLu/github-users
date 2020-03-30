package tim.githubusers.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import tim.githubusers.api.SchedulerProvider
import tim.githubusers.ext.with
import tim.githubusers.models.GithubRepository
import tim.githubusers.models.User

class UsersDataSource(
    private val githubRepository: GithubRepository,
    private val compositeDisposable: CompositeDisposable,
    private val scheduler: SchedulerProvider,
    private val throwableEvent: MutableLiveData<Throwable>
) : ItemKeyedDataSource<Long, User>() {


    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<User>) {
        compositeDisposable.add(
            githubRepository.getUsers(1, params.requestedLoadSize)
                .with(scheduler)
                .subscribe({
                    callback.onResult(it)
                }, {
                    throwableEvent.postValue(it)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<User>) {
        compositeDisposable.add(
            githubRepository.getUsers(params.key, params.requestedLoadSize)
                .with(scheduler)
                .subscribe({
                    callback.onResult(it)
                }, {
                    throwableEvent.postValue(it)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<User>) {

    }

    override fun getKey(item: User): Long {
        return item.id
    }
}
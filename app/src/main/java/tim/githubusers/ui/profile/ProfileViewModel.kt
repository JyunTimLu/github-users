package tim.githubusers.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.liveData
import io.reactivex.observers.DisposableObserver
import kotlinx.coroutines.runBlocking
import tim.githubusers.api.SchedulerProvider
import tim.githubusers.ext.with
import tim.githubusers.models.GithubRepository
import tim.githubusers.models.User
import tim.githubusers.ui.base.BaseViewModel

class ProfileViewModel(
    private val repo: GithubRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

    fun getUser(): LiveData<User> {
        val onUserDataLoadedEvent = MutableLiveData<User>()
        addDisposable {
            repo.getUser("timlu33").with(scheduler)
                .subscribeWith(object : DisposableObserver<User>() {

                    override fun onStart() {
                    }

                    override fun onComplete() {
                    }

                    override fun onNext(t: User) {
                        onUserDataLoadedEvent.postValue(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("TAG", e.toString())
                    }

                })
        }
        return onUserDataLoadedEvent
    }
}
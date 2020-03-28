package tim.githubusers.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableObserver
import tim.githubusers.api.SchedulerProvider
import tim.githubusers.ext.with
import tim.githubusers.models.GithubRepository
import tim.githubusers.models.User
import tim.githubusers.ui.base.BaseViewModel

class HomeViewModel(
    private val repo: GithubRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    init {
        getUsers()
    }


    fun getUsers() {
        addDisposable {
            repo.getUsers(0)
                .with(scheduler)
                .subscribeWith(object : DisposableObserver<List<User>>() {

                    override fun onStart() {
                        isLoading.value = true
                    }

                    override fun onComplete() {
                        isLoading.value = true
                    }

                    override fun onNext(t: List<User>) {
                        Log.d("Debug", t.toString())
                    }

                    override fun onError(e: Throwable) {
                        isLoading.value = false
                    }

                })
        }
    }
}
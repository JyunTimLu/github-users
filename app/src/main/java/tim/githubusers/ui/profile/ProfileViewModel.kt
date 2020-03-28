package tim.githubusers.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableObserver
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

    init {
        getUser()
    }


    private fun getUser() {

        addDisposable {
            repo.getUser(6395079).with(scheduler).with(scheduler)
                .subscribeWith(object : DisposableObserver<User>() {

                    override fun onStart() {
                        isLoading.value = true
                    }

                    override fun onComplete() {
                        isLoading.value = false
                    }

                    override fun onNext(t: User) {
                        Log.d("Debug", t.toString())
                    }

                    override fun onError(e: Throwable) {
                        isLoading.value = false
                    }

                })
        }

    }
}
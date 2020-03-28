package tim.githubusers.ui.base

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import tim.githubusers.ext.default

abstract class BaseViewModel : ViewModel() {

    private val disposables by lazy { CompositeDisposable() }
    val isLoading = MutableLiveData<Boolean>().default(false)

    fun addDisposable(job: () -> Disposable) {
        disposables.add(job())
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}

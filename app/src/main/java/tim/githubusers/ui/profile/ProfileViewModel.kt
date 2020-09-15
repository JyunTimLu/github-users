package tim.githubusers.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tim.githubusers.api.ResultOf
import tim.githubusers.models.GithubRepository
import tim.githubusers.models.User

class ProfileViewModel(
    private val repo: GithubRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

    fun getUser(): LiveData<ResultOf<User>> {
        val onUserDataLoadedEvent = MutableLiveData<ResultOf<User>>()
        viewModelScope.launch {
            try {
                val user = repo.getUser("timlu33")
                onUserDataLoadedEvent.postValue(ResultOf.Success(value = user))
            } catch (e: Exception) {
                onUserDataLoadedEvent.postValue(ResultOf.Failure(message = e.message, throwable = e))
            }
        }
        return onUserDataLoadedEvent
    }
}
package tim.githubusers.models

import io.reactivex.Observable
import tim.githubusers.api.GithubServices

class GithubRepository(private val githubServices: GithubServices) {

    fun getUsers(since: Int): Observable<List<User>> {
        return githubServices.getUsers(since)
    }

    fun getUser(id: Int): Observable<User> {
        return githubServices.getUser(id)
    }

}
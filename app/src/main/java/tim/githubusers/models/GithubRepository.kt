package tim.githubusers.models

import io.reactivex.Observable
import tim.githubusers.api.GithubServices

class GithubRepository(private val githubServices: GithubServices) {

    fun getUsers(since: Long, perPage: Int): Observable<List<User>> {
        return githubServices.getUsers(since, perPage)
    }

    fun getUser(id: Int): Observable<User> {
        return githubServices.getUser(id)
    }

}
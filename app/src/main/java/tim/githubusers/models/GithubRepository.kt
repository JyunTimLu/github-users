package tim.githubusers.models

import tim.githubusers.api.GithubServices

class GithubRepository(private val githubServices: GithubServices) {

    suspend fun getUsers(since: Long, perPage: Int): List<User> {
        return githubServices.getUsers(since, perPage)
    }

    suspend fun getUser(userName: String): User {
        return githubServices.getUser(userName)
    }

}
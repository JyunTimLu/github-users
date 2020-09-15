package tim.githubusers.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tim.githubusers.models.User

interface GithubServices {

    @GET("users")
    suspend fun getUsers(@Query("since") since: Long, @Query("per_page") perPage: Int): List<User>

    @GET("users/{userName}")
    suspend fun getUser(@Path("userName") userName: String): User

}
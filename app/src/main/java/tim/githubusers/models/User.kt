package tim.githubusers.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val avatar_url: String,
    val html_url: String,
    val id: Long,
    val login: String,
    val subscriptions_url: String,
    val type: String,
    val location: String?
)
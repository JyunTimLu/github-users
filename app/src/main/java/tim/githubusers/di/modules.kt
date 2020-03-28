package tim.githubusers.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tim.githubusers.api.GithubServices
import tim.githubusers.api.SchedulerProvider
import tim.githubusers.models.GithubRepository
import tim.githubusers.ui.home.HomeViewModel
import tim.githubusers.ui.profile.ProfileViewModel
import java.util.concurrent.TimeUnit

val viewModelModules = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
}

val networkModules = module {
    single { createNetworkService<GithubServices>(get(), "https://api.github.com/") }
    single { createOkHttpClient() }
}

val repositoryModules = module {
    single { GithubRepository(get()) }
}

val schedulerModule = module {
    single { SchedulerProvider()}
}


val diModules = listOf(
    viewModelModules,
    networkModules,
    repositoryModules,
    schedulerModule
)


inline fun <reified T> createNetworkService(okHttpClient: OkHttpClient, baseUrl: String): T {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(T::class.java)
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .build()
}
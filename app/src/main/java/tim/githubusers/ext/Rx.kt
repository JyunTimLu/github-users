package tim.githubusers.ext

import io.reactivex.Observable
import tim.githubusers.api.SchedulerProvider

fun <T> Observable<T>.with(schedulerProvider: SchedulerProvider): Observable<T> =
    observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())
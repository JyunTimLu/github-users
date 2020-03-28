package tim.githubusers.api

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider {

    fun io(): Scheduler = Schedulers.io()

    fun ui(): Scheduler = AndroidSchedulers.mainThread()

    fun computation(): Scheduler = Schedulers.computation()

    fun single(): Scheduler = Schedulers.single()
}
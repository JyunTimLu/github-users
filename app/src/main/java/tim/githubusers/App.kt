package tim.githubusers

import android.app.Application
import org.koin.core.context.startKoin
import tim.githubusers.di.diModules

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(diModules)
        }

    }

}
package tim.githubusers

import android.app.Application
import okhttp3.mockwebserver.MockWebServer
import org.koin.android.ext.android.inject
import org.koin.core.context.startKoin
import tim.githubusers.common.Configs
import tim.githubusers.di.diModules
import kotlin.concurrent.thread

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    private val mockWebServer by inject<MockWebServer>()

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            modules(diModules)
        }

        thread {
            mockWebServer.start()
            Configs.MOCK_API_URL =
                "http://${mockWebServer.hostName}:${mockWebServer.port}/"
        }


    }

}
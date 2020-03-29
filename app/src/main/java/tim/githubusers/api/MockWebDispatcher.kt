package tim.githubusers.api

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import tim.githubusers.App

class MockWebDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when {
            request.path.startsWith("/users") && !request.path.contains("6395079") -> getMockResponse200("json/users.json")
            request.path.startsWith("/users") && request.path.contains("6395079")-> getMockResponse200("json/user.json")
            else -> MockResponse()
        }
    }


    private fun getMockResponse200(fileName: String): MockResponse {
        return MockResponse()
            .setResponseCode(200)
            .addHeader("Content-Type", "application/json;charset=utf-8")
            .setBody(getFileFromAssets(fileName))
    }

    private fun getFileFromAssets(fileName: String): String {
        val `is` = App.instance
            .assets
            .open(fileName)
        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        return String(buffer)
    }

}
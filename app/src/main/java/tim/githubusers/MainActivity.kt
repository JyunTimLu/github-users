package tim.githubusers

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import tim.githubusers.api.BaseResp
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val user = User("123")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


        val typeA: Type = Types.newParameterizedType(BaseResp::class.java, User::class.java)

        val adapter: JsonAdapter<BaseResp<User>> = moshi.adapter(typeA)

        val toJson = adapter.toJson(BaseResp(data = user, message = "321"))
        val fromJson = adapter.fromJson(toJson)

        Log.d("TAG", toJson)
        Log.d("TAG", fromJson?.message.orEmpty())

    }
}

data class User(
    val name: String
)

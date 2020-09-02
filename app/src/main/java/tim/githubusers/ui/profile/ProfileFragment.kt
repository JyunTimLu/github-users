package tim.githubusers.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import coil.api.load
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.ext.android.inject
import tim.githubusers.R

class ProfileFragment : Fragment() {

    private val profileViewModel by inject<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        profileViewModel.getUser().observe(viewLifecycleOwner) {
            image_view.load(it.avatar_url)
            tv_name.text = it.login
            tv_name2.text = it.login
            tv_location.text = it.location
            tv_link.text = it.html_url
        }

        return root
    }
}
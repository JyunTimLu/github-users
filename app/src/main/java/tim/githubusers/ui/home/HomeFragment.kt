package tim.githubusers.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import tim.githubusers.R

class HomeFragment : Fragment() {

    private val homeViewModel by inject<HomeViewModel>()
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersAdapter = UsersAdapter()
        recycler_view.adapter = usersAdapter
        homeViewModel.usersList.observe(viewLifecycleOwner, Observer {
            usersAdapter.submitList(it)
        })

        homeViewModel.throwableEvent.observe(viewLifecycleOwner, Observer {
            it?.let {
                Snackbar.make(root_view, it.toString(), Snackbar.LENGTH_LONG)
                    .show()
            }
        })
    }
}
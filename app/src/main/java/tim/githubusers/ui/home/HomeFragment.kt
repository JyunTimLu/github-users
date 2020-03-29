package tim.githubusers.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import tim.githubusers.R

class HomeFragment : Fragment() {

    private val homeViewModel by inject<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = UsersAdapter(homeViewModel.usersList, R.layout.item_user)
        homeViewModel.getUsers().observe(viewLifecycleOwner, Observer {
            it?.let {
                recycler_view.adapter?.notifyDataSetChanged()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Debug", "killed")
    }
}
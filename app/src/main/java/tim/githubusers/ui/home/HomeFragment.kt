package tim.githubusers.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.android.ext.android.inject
import tim.githubusers.R

class HomeFragment : Fragment() {

    private val homeViewModel by inject<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        homeViewModel.getUsers().observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Debug", "killed")
    }
}
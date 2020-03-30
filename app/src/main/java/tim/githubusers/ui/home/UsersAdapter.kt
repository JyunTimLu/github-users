package tim.githubusers.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import tim.githubusers.R
import tim.githubusers.databinding.ItemUserBinding
import tim.githubusers.models.User

class UsersAdapter : PagedListAdapter<User, BindingHolder>(userDiffCallback) {

    companion object {
        val userDiffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return BindingHolder(view)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.binding?.apply {
            if (this is ItemUserBinding) {
                val user = getItem(position) as User
                imageView.load(user.avatar_url)
                tvName.text = user.login
            }
        }
    }

}


class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: ViewDataBinding? = DataBindingUtil.bind(itemView)
}
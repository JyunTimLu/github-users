package tim.githubusers.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import tim.githubusers.databinding.ItemUserBinding
import tim.githubusers.models.User

class UsersAdapter<out T>(
    private val items: List<T>,
    @LayoutRes
    private val layoutId: Int
) : RecyclerView.Adapter<BindingHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return BindingHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.binding?.apply {
            if (this is ItemUserBinding) {
                val user = items[position] as User
                imageView.load(user.avatar_url)
                tvName.text = user.login
            }
        }
    }


}


class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: ViewDataBinding? = DataBindingUtil.bind(itemView)
}
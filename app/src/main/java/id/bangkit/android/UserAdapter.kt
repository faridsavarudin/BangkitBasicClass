package id.bangkit.android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter internal constructor(private val context: Context) : RecyclerView.Adapter<UserAdapter.CarViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback
    internal var listUser = arrayListOf<User>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val ivPicture: ImageView = itemView.findViewById(R.id.iv_item_photo)
        val tvUsername: TextView = itemView.findViewById(R.id.tv_item_username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount() = listUser.size


    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val user = listUser[position]

        holder.tvName.text = user.name
        holder.tvUsername.text = user.username

        user.avatar?.let { holder.ivPicture.setImageResource(it) }
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}
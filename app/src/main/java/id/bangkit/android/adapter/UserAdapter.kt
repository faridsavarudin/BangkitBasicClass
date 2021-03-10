package id.bangkit.android.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.bangkit.android.R
import id.bangkit.android.databinding.ItemUserBinding
import id.bangkit.android.model.ItemUser

class UserAdapter : RecyclerView.Adapter<UserAdapter.CarViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback
    internal var listUser = arrayListOf<ItemUser>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: MutableList<ItemUser>) {
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemUserBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount() = listUser.size


    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val user = listUser[position]

        holder.binding.tvItemName.text = user.login
        holder.binding.tvItemUsername.text = user.type

        Glide.with(holder.itemView.context)
            .load(user.avatarUrl)
            .circleCrop()
            .into(holder.binding.ivItemPhoto)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemUser)
    }
}
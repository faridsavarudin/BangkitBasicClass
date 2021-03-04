package id.bangkit.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(private val listCar: List<Car>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val ivPicture: ImageView = itemView.findViewById(R.id.iv_item_photo)
        val tvModel: TextView = itemView.findViewById(R.id.tv_item_model)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_item_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount() = listCar.size


    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = listCar[position]

        holder.tvName.text = car.carName
        holder.tvModel.text = car.carModel
        holder.tvPrice.text = car.carPrice
        holder.ivPicture.setImageResource(car.carImage)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listCar[holder.adapterPosition])
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Car)
    }
}
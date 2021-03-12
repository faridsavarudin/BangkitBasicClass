package id.bangkit.consumerapp

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter :
    RecyclerView.Adapter<UserAdapter.MovieViewHolder>() {
    private var mCursor: Cursor? = null

    val COLUMN_TITLE = "login"
    val COLUMN_DESCRIPTION = "type"
    val COLUMN_AVATAR = "avatarUrl"

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MovieViewHolder {
        val mView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false)
        return MovieViewHolder(mView)
    }

    override fun onBindViewHolder(movieViewHolder: MovieViewHolder, i: Int) {
        movieViewHolder.bind(mCursor!!.moveToPosition(i))
    }

    override fun getItemCount(): Int {
        return if (mCursor == null) 0 else mCursor!!.count
    }

    fun setData(cursor: Cursor?) {
        mCursor = cursor
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(moveToPosition: Boolean) {
            if (moveToPosition) {
                itemView.apply {
                    tv_item_name.text =
                        mCursor?.getString(mCursor!!.getColumnIndexOrThrow(COLUMN_TITLE))
                    tv_item_username.text =
                        mCursor?.getString(mCursor!!.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                    Glide.with(context).load(mCursor!!.getString(
                            mCursor!!.getColumnIndexOrThrow(COLUMN_AVATAR)
                        )
                    ).into(iv_item_photo)

                }
             }
        }
    }
}

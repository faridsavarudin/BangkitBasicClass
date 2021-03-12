package id.bangkit.android.widget

import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.bangkit.android.R
import id.bangkit.android.db.UserDB
import id.bangkit.android.db.UserDao
import id.bangkit.android.model.ItemUser
import java.lang.Exception

class FavoriteRemoteViewsFactory : RemoteViewsService.RemoteViewsFactory {

    var  movieList = ArrayList<ItemUser>()
    var mdatabase : UserDB?=null
    private var dao: UserDao? = null
    private val mContext: Context

    override fun onCreate() {
        val identityToken = Binder.clearCallingIdentity()
        mdatabase = UserDB.getAppDataBase(mContext)
        Binder.restoreCallingIdentity(identityToken)

    }

    constructor(context: Context) {
        this.mContext = context
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun onDataSetChanged() {
        try {
        dao = mdatabase?.userDAO
        movieList.clear()
            dao?.getAllData()?.let { movieList.addAll(it) }
        }catch(e : Exception){
            Log.e("exception data changed", e.message.toString())
        }
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getViewAt(i: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.item_widget)
        try {
            val bitmap = Glide.with(mContext)
                .asBitmap()
                .load(movieList[i].avatarUrl)
                .apply(RequestOptions().fitCenter())
                .submit(800, 550)
                .get()

            rv.setImageViewBitmap(R.id.imageView, bitmap)
        } catch (e: Exception) {
            Log.e("errorr ", e.message.toString())
            e.printStackTrace()
        }


        val extras = Bundle()
        extras.putInt(FavoriteWidget.EXTRA_ITEM, i)
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
        return rv

    }

    override fun getCount(): Int {
        return movieList.size
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun onDestroy() {
        mdatabase?.close()
    }

}

package id.bangkit.android.widget

import android.content.Intent
import android.widget.RemoteViewsService

class FavoriteService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return FavoriteRemoteViewsFactory(this.applicationContext)
    }
}

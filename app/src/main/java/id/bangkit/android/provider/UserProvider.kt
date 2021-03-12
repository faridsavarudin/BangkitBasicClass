package id.bangkit.android.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import id.bangkit.android.db.UserDB
import id.bangkit.android.db.UserDao

class UserProvider : ContentProvider() {
    private var userDatabase: UserDB? = null
    private lateinit var userDAO : UserDao

    companion object {
        private const val DBNAME = "user"
        private const val DB_TABLE = "tb_user"
        private const val AUTHORITY = "id.bangkit.android.provider"
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private const val CODE_FAV_DIR = 1
        private const val CODE_FAV_ITEM = 2

        init {
            uriMatcher.addURI(
                AUTHORITY,
                DB_TABLE,
                CODE_FAV_DIR
            )
            uriMatcher.addURI(
                AUTHORITY,
                "$DB_TABLE/#",
                CODE_FAV_ITEM
            )
        }
    }

    override fun onCreate(): Boolean {
        userDatabase =
            Room.databaseBuilder(context!!, UserDB::class.java, DBNAME)
                .build()
        userDAO = userDatabase?.userDAO!!
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val code = uriMatcher.match(uri)
        return if (code == CODE_FAV_DIR || code == CODE_FAV_ITEM) {
            val context = context ?: return null
            val cursor: Cursor = if (code == CODE_FAV_DIR) userDAO.getAllData2() else userDAO.selectById(
                ContentUris.parseId(uri)
            )
            cursor.setNotificationUri(context.contentResolver, uri)
            cursor
        } else {
            throw IllegalArgumentException("Unknown Uri: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}
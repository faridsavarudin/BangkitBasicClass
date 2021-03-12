package id.bangkit.consumerapp

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import id.bangkit.consumerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private var adapter: UserAdapter? = null
    private lateinit var binding: ActivityMainBinding

    val TABLE_NAME = "tb_user"
    val AUTHORITY = "id.bangkit.android.provider"
    val CONTENT_URI = Uri.Builder().scheme("content")
        .authority(AUTHORITY)
        .appendPath(TABLE_NAME)
        .build()

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        when (id) {
            1 -> return CursorLoader(
                applicationContext,
                CONTENT_URI,
                null,
                null,
                null,
                null
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUser.layoutManager = LinearLayoutManager(applicationContext)
        adapter = UserAdapter()
        binding.rvUser.adapter = adapter
        supportLoaderManager.initLoader(1, null, this)
    }


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
        if (loader.id === 1) {
            try {
                adapter?.setData(data)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (loader.id === 1) {
            adapter?.setData(null)
        }
    }
}

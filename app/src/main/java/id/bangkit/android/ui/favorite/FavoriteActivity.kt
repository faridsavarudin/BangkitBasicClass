package id.bangkit.android.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.bangkit.android.R
import id.bangkit.android.adapter.UserAdapter
import id.bangkit.android.databinding.ActivityFavoriteBinding
import id.bangkit.android.db.UserDB
import id.bangkit.android.db.UserDao
import id.bangkit.android.model.ItemUser
import id.bangkit.android.ui.detail.DetailUserActivity

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter
    private var db: UserDB? = null
    private var dao: UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.favorite_user)

        initListFavorite()
        initLocalDB()
        showDataFavorite()
    }

    private fun initListFavorite() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter{
            showUserSelected(it)
        }
        binding.rvUser.adapter = adapter

    }

    private fun showUserSelected(data: ItemUser) {
        val detailCar = Intent(this, DetailUserActivity::class.java)
        detailCar.apply {
            putExtra(DetailUserActivity.STATE_INTENT, data)
        }
        startActivity(detailCar)
    }

    private fun showDataFavorite() {
        dao?.getAllFavUser()?.observe(this, Observer { result ->
            binding.run {
                progress.visibility = View.GONE
                if (result.size > 0) {
                    adapter.setData(result)
                    rvUser.visibility = View.VISIBLE
                    lvEmptyData.visibility = View.GONE
                } else {
                    rvUser.visibility = View.GONE
                    lvEmptyData.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun initLocalDB() {
        db = UserDB.getAppDataBase(this)
        dao = db?.userDAO
    }
}
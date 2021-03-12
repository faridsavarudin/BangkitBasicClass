package id.bangkit.android.ui.detail

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.bangkit.android.R
import id.bangkit.android.adapter.DetailUserPagerAdapter
import id.bangkit.android.databinding.ActivityDetailUserBinding
import id.bangkit.android.db.UserDB
import id.bangkit.android.db.UserDao
import id.bangkit.android.model.ItemUser
import id.bangkit.android.ui.setting.SettingActivity

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var itemUser: ItemUser
    private var username: String? = null
    private var dao: UserDao? = null
    private var db: UserDB? = null
    var available: Boolean = false

    companion object {
        const val STATE_INTENT = "DATA"
        private lateinit var detailUserViewModel: DetailUserViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.detail_user)
        itemUser = intent.getParcelableExtra<ItemUser>(STATE_INTENT) as ItemUser

        initDB()
        detailUserViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(DetailUserViewModel::class.java)
        detailUserViewModel.detailUser(itemUser.login)
        detailUserViewModel.getDetailUser().observe(this, userDetail)

    }

    private val userDetail = Observer<ItemUser> { result ->
        username = result.login.toString()
        binding.tvCompany.text = result.company
        binding.tvFollowers.text = result.followers
        binding.tvFollowing.text = result.following
        binding.tvItemName.text = result.type
        binding.tvItemUsername.text = result.login
        binding.tvRepository.text = result.publicRepos
        binding.tvLocation.text = result.location
        Glide.with(this).load(itemUser.avatarUrl).into(binding.ivAvatar)

        setupViewPager()
        binding.progress.visibility = View.GONE
    }

    private fun initDB() {
        db = UserDB.getAppDataBase(this)
        dao = db?.userDAO
        checkLike()
    }

    private fun checkLike() {
        db?.userDAO?.selectById(itemUser.id)?.observe(this, Observer {
            available = it != null
        })
    }

    fun getUsername() = username

    private fun setupViewPager() {
        val sectionsPagerAdapter = DetailUserPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        if (available) {
            menu?.getItem(0)?.setIcon(R.drawable.ic_baseline_favorite_24)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.change_language -> {
                startActivity(Intent(applicationContext, SettingActivity::class.java))
                return super.onOptionsItemSelected(item)
            }
            R.id.share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, itemUser.login)
                sharingIntent.putExtra(Intent.EXTRA_TEXT, itemUser.login)
                startActivity(Intent.createChooser(sharingIntent, "Share")
                )
                return super.onOptionsItemSelected(item)
            }
            R.id.favorite -> {
                if (available) {
                    try {
                        dao?.deleteById(itemUser.id)
                        item.setIcon(R.drawable.ic_baseline_favorite_border_24)
                        setResult(RESULT_OK)
                        val brIntent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
                        sendBroadcast(brIntent)
                    } catch (e: SQLiteConstraintException) {
                        Toast.makeText(this, e.localizedMessage, Toast.LENGTH_LONG).show()
                    }

                } else {
                    try {
                        dao?.insert(itemUser)
                        item.setIcon(R.drawable.ic_baseline_favorite_24)
                        setResult(RESULT_OK)
                        val brIntent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
                        sendBroadcast(brIntent)
                    } catch (e: SQLiteConstraintException) {
                        Toast.makeText(this, e.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }
                return super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
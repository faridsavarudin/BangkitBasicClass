package id.bangkit.android.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.bangkit.android.R
import id.bangkit.android.adapter.SectionsPagerAdapter
import id.bangkit.android.databinding.ActivityDetailUserBinding
import id.bangkit.android.model.ItemUser

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var itemUser: ItemUser
    private var username: String ?= null

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

    fun getUsername() = username

    private fun setupViewPager() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.change_language -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
                return super.onOptionsItemSelected(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
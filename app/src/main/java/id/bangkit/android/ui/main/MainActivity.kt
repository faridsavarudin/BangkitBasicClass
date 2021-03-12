package id.bangkit.android.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.bangkit.android.R
import id.bangkit.android.adapter.UserAdapter
import id.bangkit.android.databinding.ActivityMainBinding
import id.bangkit.android.model.ItemUser
import id.bangkit.android.ui.detail.DetailUserActivity
import id.bangkit.android.ui.favorite.FavoriteActivity
import id.bangkit.android.ui.setting.SettingActivity
import id.bangkit.android.utils.hideKeyboard

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    companion object{
        private lateinit var searchViewModel: SearchViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.main_page)
        binding.rvUser.setHasFixedSize(true)
        initRecyclerView()
        searchUser()
    }

    private val userList = Observer<MutableList<ItemUser>> { result ->
        showLoading(false)
        if (result.size >0){
            adapter.setData(result)
            binding.rvUser.visibility = View.VISIBLE
            binding.lvEmptyData.visibility = View.GONE
        } else {
            binding.rvUser.visibility = View.GONE
            binding.lvEmptyData.visibility = View.VISIBLE
        }

    }

    private fun searchUser() {
        binding.etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            if (query.isEmpty()) {
                return true
            } else {
                hideKeyboard()
                showLoading(true)
                getUserSearch(query)
            }
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            return false
        }
    })
    }

    private fun getUserSearch(query: String) {
        searchViewModel.searchUser(query)
        searchViewModel.getUsers().observe(this, userList)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun initRecyclerView() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter()
        binding.rvUser.adapter = adapter
        searchViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()).get(SearchViewModel::class.java)
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ItemUser) {
                showUserSelected(data)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorites, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                startActivity(Intent(applicationContext, FavoriteActivity::class.java))
                return super.onOptionsItemSelected(item)
            }
            R.id.change_language -> {
                startActivity(Intent(applicationContext, SettingActivity::class.java))
                return super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showUserSelected(data: ItemUser) {
        val detailCar = Intent(this@MainActivity, DetailUserActivity::class.java)
        detailCar.apply {
            putExtra(DetailUserActivity.STATE_INTENT, data)
        }
        startActivity(detailCar)
    }
}
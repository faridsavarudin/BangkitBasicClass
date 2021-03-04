package id.bangkit.android

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvUsers: RecyclerView
    private var list: ArrayList<User> = arrayListOf()

    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataLocations: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataPhoto: TypedArray
    private lateinit var dataFollowers: Array<String>
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Github User's"

        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(this)
        rvUsers.adapter = adapter

        prepareLoad()
        addItem()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                showCarSelected(data)
            }

        })
    }

    private fun addItem() {
            for (position in dataName.indices) {
                val hero = User(
                    dataUsername[position],
                    dataName[position],
                    dataFollowers[position],
                    dataFollowing[position],
                    dataPhoto.getResourceId(position, -1),
                    dataLocations[position],
                    dataCompany[position],
                    dataRepository[position]
                )
                list.add(hero)
            }
            adapter.listUser = list
    }

    private fun prepareLoad() {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataPhoto = resources.obtainTypedArray(R.array.avatar)
        dataCompany = resources.getStringArray(R.array.company)
        dataFollowers = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        dataLocations = resources.getStringArray(R.array.location)
        dataRepository = resources.getStringArray(R.array.repository)
    }

    private fun showCarSelected(data: User) {
        val detailCar = Intent(this@MainActivity, DetailUserActivity::class.java)
        detailCar.apply {
            putExtra(DetailUserActivity.STATE_INTENT, data)

        }
        startActivity(detailCar)
    }
}
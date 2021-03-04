package id.bangkit.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvCars: RecyclerView
    private var list: ArrayList<Car> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "My Nissan Car"

        rvCars = findViewById(R.id.rv_cars)
        rvCars.setHasFixedSize(true)
        list.addAll(CarsData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvCars.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = CarAdapter(list)
        rvCars.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : CarAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Car) {
                showCarSelected(data)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.profile -> {
                val moveToAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveToAbout)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showCarSelected(data: Car) {
        val detailCar = Intent(this@MainActivity, DetailCarActivity::class.java)
        detailCar.apply {
            putExtra("DATA", data)

        }
        startActivity(detailCar)
    }
}
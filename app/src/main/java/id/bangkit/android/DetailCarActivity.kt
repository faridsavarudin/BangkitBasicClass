package id.bangkit.android

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_car.*


class DetailCarActivity : AppCompatActivity() {

    private lateinit var itemCar: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_car)
        supportActionBar?.title = "My Nissan Car"

        itemCar  = intent.getParcelableExtra<Car>("DATA") as Car

        tv_body_type.text = itemCar.carBodyType
        tv_label_body.text = resources.getString(R.string.body_type)
        tv_label_desc.text = resources.getString(R.string.desc)
        tv_label_price.text = resources.getString(R.string.price_label)
        tv_label_transmition.text = resources.getString(R.string.transmition)
        tv_item_model.text = itemCar.carModel
        tv_item_name.text = itemCar.carName
        tv_item_price.text = itemCar.carPrice
        tv_transmition.text = itemCar.carTransmision
        iv_item_photo.setImageResource(itemCar.carImage)
        tv_desc.text = itemCar.carDescription
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, itemCar.carName)
                sharingIntent.putExtra(Intent.EXTRA_TEXT, itemCar.carDescription)
                startActivity(Intent.createChooser(sharingIntent, "Share")
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
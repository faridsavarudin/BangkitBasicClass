package id.bangkit.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import id.bangkit.android.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var itemUser: User
    companion object {
        const val STATE_INTENT = "DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail User"

        itemUser = intent.getParcelableExtra<User>(STATE_INTENT) as User

        binding.tvCompany.text = itemUser.company
        binding.tvFollowers.text = itemUser.followers
        binding.tvFollowing.text = itemUser.following
        binding.tvItemName.text = itemUser.name
        binding.tvItemUsername.text = itemUser.username
        binding.tvRepository.text = itemUser.repository
        binding.tvLocation.text = itemUser.location

        Glide.with(this).load(itemUser.avatar).into(binding.ivAvatar)
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
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, itemUser.name)
                sharingIntent.putExtra(Intent.EXTRA_TEXT, itemUser.company)
                startActivity(Intent.createChooser(sharingIntent, "Share")
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
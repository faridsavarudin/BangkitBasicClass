package id.bangkit.android.ui.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.orhanobut.hawk.Hawk
import id.bangkit.android.R
import id.bangkit.android.databinding.ActivitySettingBinding
import id.bangkit.android.widget.NotificationReceiver

class SettingActivity : AppCompatActivity() {


    private var notificationReceiver: NotificationReceiver? = null
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.setting_page)

        Hawk.init(this).build()
        notificationReceiver = NotificationReceiver()
        checkStatus()
        binding.switchDaily.setOnCheckedChangeListener { _, isChecked ->
            Hawk.put("daily_reminder", isChecked)
            if (isChecked) {
                notificationReceiver?.setDailyReminder(applicationContext)
            } else {
                notificationReceiver?.cancelDailyReminder(applicationContext)
            }
        }

        binding.changeLanguage.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }

    }

    private fun checkStatus() {
        var dailyReminder = false
        if (Hawk.get<Boolean>("daily_reminder") != null) {
            dailyReminder = Hawk.get<Boolean>("daily_reminder")
        }
        binding.switchDaily.isChecked = dailyReminder
    }
}
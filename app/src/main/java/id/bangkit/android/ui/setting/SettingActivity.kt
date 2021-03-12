package id.bangkit.android.ui.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.orhanobut.hawk.Hawk
import id.bangkit.android.R
import id.bangkit.android.widget.NotificationReceiver
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {


    private var notificationReceiver: NotificationReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        Hawk.init(this).build()
        notificationReceiver = NotificationReceiver()
        checkStatus()
        switch_daily.setOnCheckedChangeListener { _, isChecked ->
            Hawk.put("daily_reminder", isChecked)
            if (isChecked) {
                notificationReceiver?.setDailyReminder(applicationContext)
            } else {
                notificationReceiver?.cancelDailyReminder(applicationContext)
            }
        }

        change_language.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }

    }

    private fun checkStatus() {
        var dailyReminder = false
        if (Hawk.get<Boolean>("daily_reminder") != null) {
            dailyReminder = Hawk.get<Boolean>("daily_reminder")
        }
        switch_daily.isChecked = dailyReminder
    }
}
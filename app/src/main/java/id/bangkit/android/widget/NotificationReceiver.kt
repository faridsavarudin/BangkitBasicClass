package id.bangkit.android.widget

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import id.bangkit.android.R
import id.bangkit.android.ui.main.MainActivity
import java.util.*


class NotificationReceiver() : BroadcastReceiver() {

    private val DAILY_REMINDER = 1000
    private val TYPE = "type"
    private val DAILY = "daily"


    override fun onReceive(context: Context, intent: Intent?) {
        val type = intent?.getStringExtra(TYPE)

        if (type == DAILY) {
            val NOTIFICATION_ID = 1
            val CHANNEL_ID = "Channel_1"

            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context, NOTIFICATION_ID, intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            val ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        context.resources,
                        R.drawable.ic_launcher_background
                    )
                )
                .setContentTitle(context.resources.getString(R.string.app_name))
                .setContentText(context.resources.getString(R.string.daily_reminder))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSound(ringtone)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val notification = builder.build()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "Daily Reminder",
                    NotificationManager.IMPORTANCE_DEFAULT)

                builder.setChannelId(CHANNEL_ID)

                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(NOTIFICATION_ID, notification)
        }


    }

    fun setDailyReminder(_context : Context) {
        val pendingIntent =
            PendingIntent.getBroadcast(_context, DAILY_REMINDER, getReminderIntent(DAILY, _context), 0)

        val alarmManager = _context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            getReminderTime(DAILY).timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    private fun getReminderTime(type: String): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, if (type == DAILY) 9 else 10)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        return calendar
    }

    private fun getReminderIntent(type: String, _context: Context): Intent {
        val intent = Intent(_context, NotificationReceiver::class.java)
        intent.putExtra(TYPE, type)
        return intent
    }


    fun cancelDailyReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, DAILY_REMINDER, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
    }
}
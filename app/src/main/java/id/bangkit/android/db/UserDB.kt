package id.bangkit.android.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.bangkit.android.model.ItemUser

@Database(entities = [ItemUser::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase() {
    abstract val userDAO: UserDao

    companion object {
        var INSTANCE: UserDB? = null

        fun getAppDataBase(context: Context): UserDB? {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    UserDB::class.java, "user").allowMainThreadQueries().build()
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}
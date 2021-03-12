package id.bangkit.android.db

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.bangkit.android.model.ItemUser

@Dao
interface UserDao {

    @Query("select * from  tb_user")
    fun getAllData() : MutableList<ItemUser>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieItem: ItemUser)

    @Query("SELECT * FROM tb_user where id = :id")
    fun selectById(id: Int): LiveData<ItemUser>

    @Query("SELECT * FROM tb_user where id = :id")
    fun selectById(id: Long): Cursor

    @Query("DELETE FROM tb_user WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM tb_user")
    fun getAllFavUser():  LiveData<MutableList<ItemUser>>

    @Query("select * from  tb_user")
    fun getAllData2() : Cursor

}
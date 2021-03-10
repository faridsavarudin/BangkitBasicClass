package id.bangkit.android.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.bangkit.android.ApiClient
import id.bangkit.android.ApiInterface
import id.bangkit.android.ui.main.MainActivity
import id.bangkit.android.model.ItemUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {

    private val apiInterface = ApiClient.client?.create(ApiInterface::class.java)
    val itemUser = MutableLiveData<ItemUser>()

    fun detailUser( username : String?) {
        val call = apiInterface?.getDetailUsers(username)
        call?.enqueue(object : Callback<ItemUser> {
            override fun onResponse(call: Call<ItemUser>, response: Response<ItemUser>) {
                try {
                    val movies = response.body()
                    itemUser.postValue(movies)
                } catch (e: Exception) {
                    Log.d(MainActivity::class.java.simpleName, e.localizedMessage)
                }

            }

            override fun onFailure(call: Call<ItemUser>, t: Throwable) {
                t.message?.let { Log.d(MainActivity::class.java.simpleName, it) }
            }
        })
    }

    fun getDetailUser(): LiveData<ItemUser> {
        return itemUser
    }
}
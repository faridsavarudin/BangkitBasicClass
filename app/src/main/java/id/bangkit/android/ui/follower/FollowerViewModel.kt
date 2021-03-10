package id.bangkit.android.ui.follower

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.bangkit.android.ApiClient
import id.bangkit.android.ApiInterface
import id.bangkit.android.model.ItemUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel() {

    private val apiInterface = ApiClient.client?.create(ApiInterface::class.java)
    val userItem = MutableLiveData<MutableList<ItemUser>>()

    fun getFollowerQuery(query : String) {
        val call = apiInterface?.getFollowers(query)
        call?.enqueue(object : Callback<MutableList<ItemUser>> {
            override fun onResponse(call: Call<MutableList<ItemUser>>, response: Response<MutableList<ItemUser>>) {
                try {
                    val user = response.body()
                    userItem.postValue(user)
                } catch (e: Exception) {
                    Log.d("Error Followers ", e.localizedMessage)
                }

            }

            override fun onFailure(call: Call<MutableList<ItemUser>>, t: Throwable) {
                t.message?.let { Log.d("Error ", it) }
            }
        })
    }

    fun getFollowers(): LiveData<MutableList<ItemUser>> {
        return userItem
    }
}
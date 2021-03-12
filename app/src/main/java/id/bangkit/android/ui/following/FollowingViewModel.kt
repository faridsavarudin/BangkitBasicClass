package id.bangkit.android.ui.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.bangkit.android.ApiClient
import id.bangkit.android.model.ItemUser
import id.bangkit.android.rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    private val apiInterface = ApiClient.client?.create(ApiInterface::class.java)
    val userList = MutableLiveData<MutableList<ItemUser>>()

    fun getFollowingQuery(query : String) {
        val call = apiInterface?.getFollowing(query)
        call?.enqueue(object : Callback<MutableList<ItemUser>> {
            override fun onResponse(call: Call<MutableList<ItemUser>>, response: Response<MutableList<ItemUser>>) {
                try {
                    val user = response.body()
                    userList.postValue(user)
                } catch (e: Exception) {
                }

            }

            override fun onFailure(call: Call<MutableList<ItemUser>>, t: Throwable) {
                t.message?.let { Log.d("Error ", it) }
            }
        })
    }

    fun getFollowing(): LiveData<MutableList<ItemUser>> {
        return userList
    }
}
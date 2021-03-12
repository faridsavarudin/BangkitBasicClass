package id.bangkit.android.ui.follower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.bangkit.android.ApiClient
import id.bangkit.android.model.ItemUser
import id.bangkit.android.rest.ApiInterface
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
                }

            }

            override fun onFailure(call: Call<MutableList<ItemUser>>, t: Throwable) {
            }
        })
    }

    fun getFollowers(): LiveData<MutableList<ItemUser>> {
        return userItem
    }
}
package id.bangkit.android.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.bangkit.android.ApiClient
import id.bangkit.android.ApiInterface
import id.bangkit.android.model.ItemUser
import id.bangkit.android.model.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val apiInterface = ApiClient.client?.create(ApiInterface::class.java)
    val userList = MutableLiveData<MutableList<ItemUser>>()

    fun searchUser( query : String) {
        val call = apiInterface?.searchUser( query)
        call?.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                try {
                    val user = response.body()?.itemUsers
                    userList.postValue(user)
                } catch (e: Exception) {
                    Log.d(MainActivity::class.java.simpleName, e.localizedMessage)
                }

            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                t.message?.let { Log.d(MainActivity::class.java.simpleName, it) }
            }
        })
    }

    fun getUsers(): LiveData<MutableList<ItemUser>> {
        return userList
    }
}
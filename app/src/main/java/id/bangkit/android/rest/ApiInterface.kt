package id.bangkit.android

import id.bangkit.android.model.ItemUser
import id.bangkit.android.model.UsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("users/{username}/followers")
     fun getFollowers(@Path("username") movieType: String): Call<MutableList<ItemUser>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") movieType: String): Call<MutableList<ItemUser>>

    @GET("search/users")
    fun searchUser(@Query("q")  query: String): Call<UsersResponse>

    @GET("users/{username}")
    fun getDetailUsers( @Path("username") username: String?): Call<ItemUser>

}
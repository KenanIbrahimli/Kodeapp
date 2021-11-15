package com.knni.kode_app.ui.userlist

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.knni.kode_app.api.CodeApi
import com.knni.kode_app.api.Item
import com.knni.kode_app.api.UserModelResponse
import com.knni.kode_app.utils.UserListFilter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListViewModel(application: Application) : AndroidViewModel(application) {

    private var userListResponse: MutableLiveData<List<Item>> = MutableLiveData()

    var _userListResponse: MutableLiveData<List<Item>> = userListResponse

    private var allUserList: List<Item> = arrayListOf()

    private val compositeDisposable = CompositeDisposable()

    private val _allUserList = UserListFilter.userListFilter


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


    fun userRequestData(codeApi: CodeApi?) {

        val call: Call<UserModelResponse>? = codeApi!!.getUsersData()
        call?.enqueue(object : Callback<UserModelResponse> {
            override fun onResponse(
                call: Call<UserModelResponse>?,
                response: Response<UserModelResponse>?
            ) {
                if (response!!.isSuccessful) {
                    response.body().let {
                        userListResponse.postValue(it?.items)

                    }
                    allUserList.apply { this }
                    Log.d("Tag", "succesa" )
                } else {
                    userListResponse.postValue(null)
                    Log.d("Tag", "null error")
                }
            }

            override fun onFailure(call: Call<UserModelResponse>?, t: Throwable) {
                userListResponse.postValue(null)
                Log.d("Tag", "failure error")
            }
        }
        )
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }

        return false
    }


    fun filterUserList(input: String?) {
        input?.let { inputItem ->

                val result = if (input.isNotEmpty()) {
                    UserListFilter.userListFilter?.filter {
                        it.firstName.contains(inputItem, ignoreCase = true)
                    }
                } else {
                    _allUserList
                }

                userListResponse.apply {
                    this.value = result
                }

        } ?: run {
            userListResponse?.apply {
                this.value = _allUserList
            }
        }
    }
}
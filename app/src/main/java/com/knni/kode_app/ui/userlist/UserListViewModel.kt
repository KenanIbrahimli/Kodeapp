package com.knni.kode_app.ui.userlist

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.knni.kode_app.api.*
import com.knni.kode_app.utils.UserListFilter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class UserListViewModel(application: Application) : AndroidViewModel(application) {

    private var userListResponse: MutableLiveData<List<Item>> = MutableLiveData()

    var _userListResponse: LiveData<List<Item>> = userListResponse

    var allUserListResponse: MutableLiveData<List<Item>> = MutableLiveData()

    private var allUserList: List<Item> = arrayListOf()

    private val compositeDisposable = CompositeDisposable()

   // val _allUserList = UserListFilter.userListFilter

    private var job = Job()
    private var corutineScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getUserList(codeApi: CodeApi){
         corutineScope.launch{
             var result = Repository.getUsers(codeApi)
             when(result){
                 is ResultWrapper.Success ->{
                     UserListFilter.userListFilter = result.value.items
                     withContext(Dispatchers.Main){
                     userListResponse.apply {
                         this.value = result.value.items
                     }
                     }
                 }
                 is ResultWrapper.GenericError -> {
                     var exception: Exception = result.error!!
                     UserListFilter.userListFilter = listOf()
                 }
             }
         }
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


    fun filterUserList(input: String?, allUserList: List<Item>) {

        input?.let { inputItem ->
                val result = if (input.isNotEmpty()) {
                    allUserList?.filter {
                        it.firstName.contains(inputItem, ignoreCase = true)
                    }
                } else {
                    allUserList
                }

                userListResponse.apply {
                    this.value = result
                }

        } ?: run {
            userListResponse?.apply {
                this.value = allUserList
            }
        }
    }
}
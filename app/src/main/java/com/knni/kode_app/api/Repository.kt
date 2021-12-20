package com.knni.kode_app.api

import com.knni.kode_app.utils.UserListFilter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Repository {
    suspend fun getUsers(apiInterface: CodeApi): ResultWrapper<UserModelResponse> {
        return CustomNetworkCall.safeApiCall {
            apiInterface.getUsersData()
        }
    }

}

object CustomNetworkCall {
    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        apicall: suspend () -> T
    ): ResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                val result = apicall.invoke()
                ResultWrapper.Success(result)
            } catch (throwable: Exception) {
                ResultWrapper.GenericError(throwable)
            }
        }
    }
}
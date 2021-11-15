package com.knni.kode_app.api

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CodeApi {

    @GET("users")
    @Headers("Content-Type: application/json")
    fun getUsersData(): Call<UserModelResponse>?
}

object Constants {
    const val BASE_URL = "https://stoplight.io/mocks/kode-education/trainee-test/25143926/"
}
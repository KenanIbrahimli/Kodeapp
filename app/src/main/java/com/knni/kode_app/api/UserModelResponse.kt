package com.knni.kode_app.api

data class UserModelResponse(
    val avatarUrl: String,
    val birthday: String,
    val department: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val position: String,
    val userTag: String
)
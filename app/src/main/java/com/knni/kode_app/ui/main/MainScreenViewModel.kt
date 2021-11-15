package com.knni.kode_app.ui.main

import androidx.lifecycle.ViewModel
import com.knni.kode_app.utils.UserListFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainScreenViewModel: ViewModel(){

//    private val job = Job()
//    private val coroutinesScope = CoroutineScope(Dispatchers.Main + job)
//
//    fun filterUserList(input: String?){
//        input?.let { inputItem ->
//            coroutinesScope.launch {
//                val result = if(input.isNotEmpty()){
//                    UserListFilter.userListFilter?.filter {
//                        it.firstName.contains(inputItem, ignoreCase = true) ||
//                                it.lastName.contains(inputItem, ignoreCase = true) ||
//                                it.customersurname.contains(inputItem, ignoreCase = true) ||
//                                it.fiscalid.contains(inputItem, ignoreCase = true)
//                    }
//                } else {
//                    allAvansList
//                }
//
//                _avanslist.apply {
//                    this.value = result
//                }
//            }
//        } ?: run{
//            _avanslist.apply {
//                this.value = allAvansList
//            }
//        }
//    }

}
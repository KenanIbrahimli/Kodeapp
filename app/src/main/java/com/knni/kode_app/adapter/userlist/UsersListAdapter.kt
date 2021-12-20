package com.knni.kode_app.adapter.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.knni.kode_app.R
import com.knni.kode_app.api.Item
import com.knni.kode_app.api.UserModelResponse
import com.knni.kode_app.databinding.RclUserItemBinding
import com.knni.kode_app.utils.loadImg


class UsersListAdapter(
    private val listener: (Item) -> Unit
) : RecyclerView.Adapter<UsersListAdapter.UserListViewHolder>() {
    private var userList: ArrayList<Item> = arrayListOf()


    fun emitData(newList: List<Item>?){
        val du = UserAdapterDiffUtil(userList, newList!!)
        val cDu = DiffUtil.calculateDiff(du)

        userList.clear()
        userList.addAll(newList)

        cDu.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RclUserItemBinding>(
            layoutInflater,
            R.layout._rcl_user_item,
            parent,
            false
        )
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserListViewHolder(private val binding: RclUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
                binding.userCredentials.text = (item.firstName + item.lastName).toString()
                binding.userDepartment.text = item.department
                binding.userJob.text = item.position
                binding.userImage.loadImg(item.avatarUrl)

                binding.root.setOnClickListener {
                    listener.invoke(item)
                }
            }
        }

    }


package com.knni.kode_app.adapter.userlist

import androidx.recyclerview.widget.DiffUtil
import com.knni.kode_app.api.Item

class UserAdapterDiffUtil (
    private val oldlist: ArrayList<Item>,
    private val newlist: List<Item>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldlist.size
    }

    override fun getNewListSize(): Int {
        return  newlist.size
    }

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        return oldlist[p0] == newlist[p1]
    }

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
        return oldlist[p0].id == newlist[p1].id
    }
}
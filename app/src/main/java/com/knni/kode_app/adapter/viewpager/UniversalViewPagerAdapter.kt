package com.knni.kode_app.adapter.viewpager

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.knni.kode_app.ui.userlist.UserListFragment


class UniversalViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 13

    override fun createFragment(i: Int): Fragment {
        val fragment = UserListFragment()
        fragment.arguments = Bundle().apply {

            putInt(ARG_OBJECT, i + 1)
        }
        return fragment
    }
}

const val ARG_OBJECT = "object"


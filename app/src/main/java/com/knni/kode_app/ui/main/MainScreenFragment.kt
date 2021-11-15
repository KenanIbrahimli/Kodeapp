package com.knni.kode_app.ui.main


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView

import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.knni.kode_app.R
import com.knni.kode_app.adapter.viewpager.UniversalViewPagerAdapter
import com.knni.kode_app.databinding.ActivityMainBinding
import com.knni.kode_app.databinding.FragmentMainScreenBinding
import com.knni.kode_app.ui.MainActivity
import com.knni.kode_app.ui.userlist.UserListFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainScreenFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private  lateinit var viewModel: MainScreenViewModel

    private lateinit var universalViewPagerAdapter: UniversalViewPagerAdapter
    private lateinit var viewPager: ViewPager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false)

        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        universalViewPagerAdapter = UniversalViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = universalViewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text =
                when(position){
                0 -> "All"
                    1-> "Designers"
                    2-> "Analysts"
                    3-> "Managers"
                    4->"iOS"
                    5->"Android"
                    6->"QA"
                    7->"Back-office"
                    8->"Frontend"
                    9 ->"HR"
                    10->"PR"
                    11 ->"Backend"
                    12->"Support"
                    else -> ""
            }

        }.attach()


    }



}
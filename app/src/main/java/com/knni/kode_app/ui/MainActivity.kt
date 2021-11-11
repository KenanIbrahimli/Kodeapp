package com.knni.kode_app.ui

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.knni.kode_app.R
import com.knni.kode_app.databinding.ActivityMainBinding
import com.knni.kode_app.ui.main.MainScreenFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val fragment = MainScreenFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    fun changeFragment(item: Fragment,
                       bundles: HashMap<String, String>?){
        bundles?.let {
            val bndl = Bundle()
            for(z in bundles){
                bndl.putString(z.key, z.value)
            }
            item.arguments = bndl
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, item)
            .addToBackStack("item")
            .commit()
    }
}
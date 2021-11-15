package com.knni.kode_app.ui


import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.knni.kode_app.R
import com.knni.kode_app.api.Item
import com.knni.kode_app.api.UserModelResponse
import com.knni.kode_app.databinding.ActivityMainBinding
import com.knni.kode_app.ui.main.MainScreenFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


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

    fun clearBackStack(){
        val backStackCount = supportFragmentManager.backStackEntryCount

        if (backStackCount >= 1) {
            val fm = supportFragmentManager
            for (i in 0 until fm.backStackEntryCount) {
                fm.popBackStack()
            }

        }
    }

    //loader start
    fun createLoader(title: String, color: Int){
        runOnUiThread{
            main_loader.visibility = View.VISIBLE
            main_loader.text = title
            main_loader_cv.setCardBackgroundColor(color)
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

    //loader dismiss
    fun dismissLoader(){
        runOnUiThread{
            main_loader.visibility = View.GONE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }
//
//
//    /*Dialog notification*/
//    fun makeDialogNote(title: String, desc: String, status: Int){
//        val dialog = MainDialogFragment()
//        val bndl = Bundle()
//        bndl.putString("title", title)
//        bndl.putString("desc", desc)
//        bndl.putInt("status", status)
//        dialog.arguments = bndl
//
//        dialog.show(supportFragmentManager, "dialog")
//    }
//

}
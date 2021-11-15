package com.knni.kode_app.ui.detailedInfo

import android.os.Bundle
import android.provider.Contacts
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.knni.kode_app.R
import com.knni.kode_app.api.Item
import com.knni.kode_app.databinding.FragmentDetailedInfoBinding
import com.knni.kode_app.databinding.FragmentDetailedInfoBindingImpl
import com.knni.kode_app.utils.convertDate
import com.knni.kode_app.utils.loadImg
import java.text.SimpleDateFormat
import java.util.*


class DetailedInfoFragment : Fragment() {

    private lateinit var binding: FragmentDetailedInfoBinding
    var date = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_detailed_info, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var avatarUrl = arguments?.getString("avatarUrl")

        var birthday = arguments?.getString("birthday")
        var department = arguments?.getString("department")
        var firstName = arguments?.getString("firstName")
        var lastName = arguments?.getString("lastName")
        var phone = arguments?.getString("phone")
        var position = arguments?.getString("position")

        var birt = "birthday".convertDate(birthday!!)
        binding.userImage.loadImg(avatarUrl!!)
        binding.credentials.text = "$firstName  $lastName"
        binding.birtday.text = birt
        binding.old.text = "${(date.get(Calendar.YEAR)).minus((birthday?.take(4))!!.toInt()).toString()} года"
        binding.phoneNumber.text = phone
        binding.position.text = position
    }

}
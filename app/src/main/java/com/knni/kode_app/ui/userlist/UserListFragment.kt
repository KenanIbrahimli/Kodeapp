package com.knni.kode_app.ui.userlist

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.knni.kode_app.MainApplication
import com.knni.kode_app.R
import com.knni.kode_app.adapter.userlist.UsersListAdapter
import com.knni.kode_app.adapter.viewpager.ARG_OBJECT
import com.knni.kode_app.api.Item
import com.knni.kode_app.databinding.FragmentUserListBinding
import com.knni.kode_app.ui.MainActivity
import com.knni.kode_app.ui.detailedInfo.DetailedInfoFragment
import com.knni.kode_app.utils.UserListFilter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class UserListFragment : Fragment(), SearchView.OnQueryTextListener {


    private lateinit var binding: FragmentUserListBinding
    private var viewModel: UserListViewModel? = null

    private var userList: ArrayList<Item> = arrayListOf()
    private var allUserList: ArrayList<Item> = arrayListOf()

    private var filteredUserList: ArrayList<Item> = arrayListOf()
    private var filterLiveData: MutableLiveData<List<Item>> = MutableLiveData()

    var filterList: List<Item> = listOf()

    private lateinit var userAdapter: UsersListAdapter

    var job = Job()
    val coroutinesScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false)
        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)

        setHasOptionsMenu(true)
        (context as MainActivity).supportActionBar

        userAdapter = UsersListAdapter {
            var params = HashMap<String, String>()
            params["avatarUrl"] = it.avatarUrl
            params["birthday"] = it.birthday
            params["department"] = it.department
            params["firstName"] = it.firstName
            params["lastName"] = it.lastName
            params["phone"] = it.phone
            params["position"] = it.position

            (context as MainActivity).changeFragment(DetailedInfoFragment(), params)
        }

        binding.userList.adapter = userAdapter
        binding.userList.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)



        if (viewModel!!.isOnline(context!!)) {
            viewModel?.userRequestData((activity?.application as MainApplication).api)
            viewModel?.allUserListResponse?.observe(viewLifecycleOwner, {allUsers ->
                if (allUsers!=null) {
                    allUsers.forEach {
                        allUserList.add(it)
                    }
                }
            })

            viewModel?._userListResponse?.observe(viewLifecycleOwner, { users ->

                if (users != null) {
                    userAdapter.emitData(users)
                    userAdapter.notifyDataSetChanged()

                } else {
                    Toast.makeText(
                        activity?.applicationContext,
                        "No data found",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        } else {
            Toast.makeText(
                activity?.applicationContext,
                "Check for internet connection",
                Toast.LENGTH_SHORT
            ).show()
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var currentTab = 0
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            currentTab = (getInt(ARG_OBJECT).toString()).toInt()
        }
        UserListFilter.userListFilter = allUserList
        Log.d("Loger", " alluserlist ${allUserList?.forEach {it.firstName}}")
        Log.d("Loger", " alluserlist ${viewModel?._allUserList?.forEach {it.firstName}}")
        Log.d("Loger", " userListFilter ${UserListFilter.userListFilter?.forEach {it.firstName}}")

//        if (viewModel!!.isOnline(context!!)) {
//            when (currentTab) {
//                0 -> userAdapter.emitData(filterList)
//                1 -> setRecyclerData(1)
//                2 -> setRecyclerData(2)
//                3 -> setRecyclerData(3)
//                4 -> setRecyclerData(4)
//                5 -> setRecyclerData(5)
//                6 -> setRecyclerData(6)
//                7 -> setRecyclerData(7)
//                8 -> setRecyclerData(8)
//                9 -> setRecyclerData(9)
//                10 -> setRecyclerData(10)
//                11 -> setRecyclerData(11)
//                12 -> setRecyclerData(12)
//            }
//
//        }
        
    }

    private fun setRecyclerData(id: Int) {
        Log.d("Tag", "function runned")
        var department = when (id) {
            0 -> "All"
            1-> "Design"
            2 -> "Analytics"
            3 -> "Management"
            4 -> "iOS"
            5 -> "Android"
            6 -> "QA"
            7 -> "Back_office"
            8 -> "Frontend"
            9 -> "HR"
            10 -> "PR"
            11 -> "Backend"
            12 -> "Support"
            else -> "All"
        }

        allUserList.forEach {
            if(department == "All"){
                filteredUserList.clear()
                filteredUserList.add(it)

                filterLiveData.apply {
                    this.value = filteredUserList
                }
            } else if(it.department.contains(department.lowercase(), ignoreCase = true)) {
                filteredUserList.clear()
                filteredUserList.add(it)

                filterLiveData.apply {
                    this.value = filteredUserList
                }
            }
            userAdapter.emitData(filterList)
            binding.userList.adapter?.notifyDataSetChanged()
            userAdapter.emitData(filterList)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.app_bar_menu, menu)
        val sort = menu?.findItem(R.id.sort_button)


        val mi = menu?.findItem(R.id.search_button)
        val sv: SearchView = mi?.actionView as SearchView

        sv.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean = false


    override fun onQueryTextChange(p0: String?): Boolean {
        viewModel?.filterUserList(p0, allUserList)
        return false
    }
}

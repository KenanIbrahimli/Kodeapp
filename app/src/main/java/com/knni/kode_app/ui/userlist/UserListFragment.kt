package com.knni.kode_app.ui.userlist

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class UserListFragment : Fragment(), SearchView.OnQueryTextListener {


    private lateinit var binding: FragmentUserListBinding
    private var viewModel: UserListViewModel? = null

    private var userList: ArrayList<Item> = arrayListOf()
    private var allUserList: ArrayList<Item> = arrayListOf()

    private var filteredUserList: ArrayList<Item> = arrayListOf()
    private var filterLiveData: MutableLiveData<List<Item>> = MutableLiveData()

    var filterList: List<Item> = listOf()

    private var tempUser = Item("","","","","","","","","")

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

        viewModel?.getUserList((activity?.application as MainApplication).api!!)


            return binding.root
        }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            var currentTab = 0
            arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
                currentTab = (getInt(ARG_OBJECT).toString()).toInt()
            }

        if (viewModel!!.isOnline(context!!)) {
            when (currentTab) {
                0 -> {
                    viewModel?._userListResponse?.observe(viewLifecycleOwner, Observer {
                        if (it != null) {
                            userAdapter.emitData(it)
                            allUserList.clear()
                            allUserList.addAll(it)
                        } else{
                            userAdapter.emitData(listOf(tempUser))
                        }
                        userAdapter.notifyDataSetChanged()
                    })
                }
                1 -> {
                    viewModel?._userListResponse?.observe(viewLifecycleOwner, Observer {
                        if (it != null){
                            var result = it.filter{ t -> t.department.contains("design")}
                            userAdapter.emitData(result)
                            allUserList.clear()
                            allUserList.addAll(it)
                        } else{
                            userAdapter.emitData(listOf(tempUser))
                        }
                        userAdapter.notifyDataSetChanged()
                    })
                }
                2 -> {
                    viewModel?._userListResponse?.observe(viewLifecycleOwner, Observer {
                        if (it != null){
                            var result = it.filter{ t -> t.department.contains("analytics")}
                            userAdapter.emitData(result)
                            allUserList.clear()
                            allUserList.addAll(it)
                        } else{
                            userAdapter.emitData(listOf(tempUser))
                        }
                        userAdapter.notifyDataSetChanged()
                    })
                }
                3 -> {
                    viewModel?._userListResponse?.observe(viewLifecycleOwner, Observer {
                        if (it != null){
                            var result = it.filter{ t -> t.department.contains("management")}
                            userAdapter.emitData(result)
                            allUserList.clear()
                            allUserList.addAll(it)
                        } else{
                            userAdapter.emitData(listOf(tempUser))
                        }
                        userAdapter.notifyDataSetChanged()
                    })
                }
                4 -> {
                    viewModel?._userListResponse?.observe(viewLifecycleOwner, Observer {
                        if (it != null){
                            var result = it.filter{ t -> t.department.contains("ios")}
                            userAdapter.emitData(result)
                            allUserList.clear()
                            allUserList.addAll(it)
                        } else{
                            userAdapter.emitData(listOf(tempUser))
                        }
                        userAdapter.notifyDataSetChanged()
                    })
                }
                5 -> {
                    viewModel?._userListResponse?.observe(viewLifecycleOwner, Observer {
                        if (it != null){
                            var result = it.filter{ t -> t.department.contains("android")}
                            userAdapter.emitData(result)
                            allUserList.clear()
                            allUserList.addAll(it)
                        } else{
                            userAdapter.emitData(listOf(tempUser))
                        }
                        userAdapter.notifyDataSetChanged()
                    })
                }
                6 -> {
                    viewModel?._userListResponse?.observe(viewLifecycleOwner, Observer {
                        if (it != null){
                            var result = it.filter{ t -> t.department.contains("qa")}
                            userAdapter.emitData(result)
                            allUserList.clear()
                            allUserList.addAll(it)
                        } else{
                            userAdapter.emitData(listOf(tempUser))
                        }
                        userAdapter.notifyDataSetChanged()
                    })
                }
                7 -> {
                    viewModel?._userListResponse?.observe(viewLifecycleOwner, Observer {
                        if (it != null){
                            var result = it.filter{ t -> t.department.contains("back_office")}
                            userAdapter.emitData(result)
                            allUserList.clear()
                            allUserList.addAll(it)
                        } else{
                            userAdapter.emitData(listOf(tempUser))
                        }
                        userAdapter.notifyDataSetChanged()
                    })
                }
                8 -> {
                    viewModel?._userListResponse?.observe(viewLifecycleOwner, Observer {
                        if (it != null){
                            var result = it.filter{ t -> t.department.contains("frontend")}
                            userAdapter.emitData(result)
                            allUserList.clear()
                            allUserList.addAll(it)
                        } else{
                            userAdapter.emitData(listOf(tempUser))
                        }
                        userAdapter.notifyDataSetChanged()
                    })
                }
                9 -> {
                    viewModel?._userListResponse?.observe(viewLifecycleOwner, Observer {
                        if (it != null){
                            var result = it.filter{ t -> t.department.contains("hr")}
                            userAdapter.emitData(result)
                            allUserList.clear()
                            allUserList.addAll(it)
                        } else{
                            userAdapter.emitData(listOf(tempUser))
                        }
                        userAdapter.notifyDataSetChanged()
                    })
                }
                10 -> {
                    viewModel?._userListResponse?.observe(viewLifecycleOwner, Observer {
                        if (it != null){
                            var result = it.filter{ t -> t.department.contains("pr")}
                            userAdapter.emitData(result)
                            allUserList.clear()
                            allUserList.addAll(it)
                        } else{
                            userAdapter.emitData(listOf(tempUser))
                        }
                        userAdapter.notifyDataSetChanged()
                    })
                }
                11 -> {
                    viewModel?._userListResponse?.observe(viewLifecycleOwner, Observer {
                        if (it != null){
                            var result = it.filter{ t -> t.department.contains("backend")}
                            userAdapter.emitData(result)
                            allUserList.clear()
                            allUserList.addAll(it)
                        } else{
                            userAdapter.emitData(listOf(tempUser))
                        }
                        userAdapter.notifyDataSetChanged()
                    })
                }
                12 -> {
                    viewModel?._userListResponse?.observe(viewLifecycleOwner, Observer {
                        if (it != null){
                            var result = it.filter{ t -> t.department.contains("support")}
                            userAdapter.emitData(result)
                            allUserList.clear()
                            allUserList.addAll(it)
                        } else{
                            userAdapter.emitData(listOf(tempUser))
                        }
                        userAdapter.notifyDataSetChanged()
                    })
                }
            }

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

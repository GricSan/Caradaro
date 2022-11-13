package com.gricsan.caradaro.features.user_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gricsan.caradaro.base.utils.safeNavigate
import com.gricsan.caradaro.databinding.FragmentUserListScreenBinding
import com.gricsan.caradaro.features.user_list.domain.models.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListScreen : Fragment() {

    private var _binding: FragmentUserListScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserListScreenViewModel by viewModels()

    private var userListAdapter: UserListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListScreenBinding.inflate(layoutInflater, container, false)
        initUserListRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupInteractions()
        observeViewState()
    }

    override fun onStart() {
        super.onStart()
        viewModel.handleEvent(UserListScreenEvent.ViewReady)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initUserListRecyclerView() {
        binding.rvUserList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UserListAdapter(this@UserListScreen::onUserListItemClicked)
                .also {
                    userListAdapter = it
                    addItemDecoration(it.itemDecoration)
                }
        }
    }

    private fun onUserListItemClicked(user: User) {
        val action = UserListScreenDirections.actionUserListScreenToVehicleLocationScreen()
        findNavController().safeNavigate(action)
    }

    private fun setupInteractions() {
        binding.swipeRefreshDrivers.setOnRefreshListener {
            viewModel.handleEvent(UserListScreenEvent.UserListRefreshRequested)
        }
    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UserListScreenViewState.Data -> {
                    binding.swipeRefreshDrivers.isRefreshing = false
                    userListAdapter?.setData(state.userList)
                }
                is UserListScreenViewState.Error -> {
                    binding.swipeRefreshDrivers.isRefreshing = false
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
                is UserListScreenViewState.Loading -> {
                    binding.swipeRefreshDrivers.isRefreshing = true
                }
            }
        }
    }

}
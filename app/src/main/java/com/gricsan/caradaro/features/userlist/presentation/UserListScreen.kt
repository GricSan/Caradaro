package com.gricsan.caradaro.features.userlist.presentation

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
import com.gricsan.caradaro.features.userlist.domain.models.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListScreen : Fragment() {

    private var _binding: FragmentUserListScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserListScreenViewModel by viewModels()

    private var userListAdapter: UserListAdapter? = null

    private var errorToast: Toast? = null


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
            layoutManager = LinearLayoutManager(context)
            adapter = UserListAdapter(this@UserListScreen::onUserListItemClicked)
                .also {
                    addItemDecoration(it.itemDecoration)
                    userListAdapter = it
                }
        }
    }

    private fun onUserListItemClicked(user: User) {
        UserListScreenDirections.actionUserListScreenToVehicleLocationScreen(user.id)
            .let { findNavController().safeNavigate(it) }
    }

    private fun setupInteractions() {
        binding.swipeRefreshDrivers.setOnRefreshListener {
            viewModel.handleEvent(UserListScreenEvent.UserListRefreshRequested)
        }
    }

    private fun observeViewState() {
        with(viewModel) {
            userListViewState.observe(viewLifecycleOwner) { state ->
                // Hide displayed error (if any)
                errorToast?.cancel()
                // Update loading state
                binding.swipeRefreshDrivers.isRefreshing = state.loading
                // Set data
                userListAdapter?.setData(state.data)
                // Display error (if any)
                state.error?.let { message ->
                    errorToast = Toast.makeText(requireContext(), message, Toast.LENGTH_LONG)
                        .also { it.show() }
                }
            }
        }
    }

}
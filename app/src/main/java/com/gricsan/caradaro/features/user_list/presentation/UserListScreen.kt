package com.gricsan.caradaro.features.user_list.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gricsan.caradaro.databinding.FragmentUserListScreenBinding
import com.gricsan.caradaro.features.user_list.domain.entities.User
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
        initUserListRecyclerView(binding)
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


    private fun initUserListRecyclerView(binding: FragmentUserListScreenBinding) {
        binding.rvUserList.apply {
            adapter = UserListAdapter(this@UserListScreen::onUserListItemClicked).also {
                userListAdapter = it
            }
            layoutManager = LinearLayoutManager(binding.root.context)
        }
    }

    private fun onUserListItemClicked(user: User) {
        Log.v("MyTag", "")
    }

    private fun setupInteractions() {

    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner) { newState ->
            updateViewState(newState)
        }
    }

    private fun updateViewState(newState: UserListScreenViewState) {
        updateUserList(newState.userList)
    }

    private fun updateUserList(userList: List<User>) {
        userListAdapter?.setData(userList)
    }

}
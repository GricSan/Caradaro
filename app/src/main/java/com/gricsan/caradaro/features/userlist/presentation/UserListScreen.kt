package com.gricsan.caradaro.features.userlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gricsan.caradaro.databinding.FragmentUserListScreenBinding

class UserListScreen : Fragment() {

    private var _binding: FragmentUserListScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserListScreenViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListScreenBinding.inflate(layoutInflater, container, false)
        setupClickListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        bindViews()
    }

    override fun onStart() {
        super.onStart()
        loadScreenData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupClickListeners() {
        binding.run {
            // Implementation code
        }
    }

    private fun setupViewModel() {
        viewModel.run {
            // Implementation code
        }
    }

    private fun bindViews() {
        viewModel.run {
            // Implementation code
        }
    }

    private fun loadScreenData() {
        viewModel.run {
            // Implementation code
        }
    }

}
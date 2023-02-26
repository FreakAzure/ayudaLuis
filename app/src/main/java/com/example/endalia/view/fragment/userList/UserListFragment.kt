package com.example.endalia.view.fragment.userList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.endalia.R
import com.example.endalia.view.fragment.register.RegisterViewModel
import kotlinx.coroutines.launch

class UserListFragment : Fragment() {
    private lateinit var viewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        observeViewModel()
        lifecycleScope.launch {
            viewModel.getUsers()
        }
    }

    fun observeViewModel() {
        viewModel.users.observe(viewLifecycleOwner) {

        }
    }
}
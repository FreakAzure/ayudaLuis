package com.example.endalia.view.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.endalia.R
import com.example.endalia.view.fragment.register.RegisterFragment
import com.example.endalia.view.fragment.userList.UserListFragment
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        observeViewModel()
        setListeners()

    }

    fun setListeners() {
        val button = view?.findViewById<Button>(R.id.loginButton)
        val registerButton = view?.findViewById<Button>(R.id.toRegisterButton)
        val emailInput = view?.findViewById<TextInputEditText>(R.id.userEmail)
        val passInput = view?.findViewById<TextInputEditText>(R.id.userPass)

        button?.setOnClickListener {
            lifecycleScope.launch {
                viewModel.performLogin()
            }
        }
        emailInput?.doOnTextChanged { text, _, _, _ ->
            // Updates viewmodel _email
            viewModel.setUserEmail(text.toString())
        }
        passInput?.doOnTextChanged { text, _, _, _ ->
            // Updates viewmodel _pass
            viewModel.setUserPass(text.toString())
        }

        registerButton?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main_frame, RegisterFragment())
                .commit()
        }
    }

    fun observeViewModel() {
        viewModel.loginSuccess.observe(viewLifecycleOwner) {
            when (it) {
                LoginViewModel.LoginState.EmailError -> {
                    // Show error on email field
                }
                LoginViewModel.LoginState.PasswordError -> {
                    // Show error on pass field
                }
                LoginViewModel.LoginState.BothError -> {
                    // Show error on both fields
                }
                LoginViewModel.LoginState.None -> {
                    // Hide error on both
                }
                LoginViewModel.LoginState.UserNotFoundError -> {
                    // Show user not found
                }
                LoginViewModel.LoginState.Success -> {
                    parentFragmentManager.beginTransaction().replace(R.id.main_frame, UserListFragment()).commit()
                }
            }
        }
    }

}
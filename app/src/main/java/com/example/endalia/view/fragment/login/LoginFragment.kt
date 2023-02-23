package com.example.endalia.view.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.endalia.R
import com.example.endalia.util.Singleton
import com.google.android.material.textfield.TextInputEditText

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
        viewModel.configViewmodel(requireContext())
        observeViewModel()
        setListeners()

    }

    fun setListeners() {
        val button = view?.findViewById<Button>(R.id.loginButton)
        val emailInput = view?.findViewById<TextInputEditText>(R.id.userEmail)
        val passInput = view?.findViewById<TextInputEditText>(R.id.userPass)

        button?.setOnClickListener {
            viewModel.performLogin()
        }
        emailInput?.doOnTextChanged { text, _, _, _ ->
            // Updates viewmodel _email
            viewModel.setUserEmail(text.toString())
        }
        passInput?.doOnTextChanged { text, _, _, _ ->
            // Updates viewmodel _pass
            viewModel.setUserPass(text.toString())
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

                }
            }
        }
    }

}
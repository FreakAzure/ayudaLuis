package com.example.endalia.view.fragment.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.endalia.R
import com.example.endalia.view.fragment.register.RegisterViewModel
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        viewModel.configViewmodel(requireContext())
        observeViewModel()
        setListeners()

    }
    private fun setListeners() {
        val button = view?.findViewById<Button>(R.id.registerButton)
        val emailInput = view?.findViewById<TextInputEditText>(R.id.userEmail)
        val passInput = view?.findViewById<TextInputEditText>(R.id.userPass)
        val confirmPassInput = view?.findViewById<TextInputEditText>(R.id.reUserPass)

        button?.setOnClickListener {
            viewModel.performRegister()
        }

        emailInput?.doOnTextChanged { text, _, _, _ ->
            // Updates viewmodel _userEmail
            viewModel.setUserEmail(text.toString())
        }

        passInput?.doOnTextChanged { text, _, _, _ ->
            // Updates viewmodel _userPass
            viewModel.setUserPass(text.toString())
        }

        confirmPassInput?.doOnTextChanged { text, _, _, _ ->
            // Updates viewmodel _confirmUserPass
            viewModel.setConfirmPass(text.toString())
        }
    }

    private fun observeViewModel() {
        viewModel.registerSuccess.observe(viewLifecycleOwner) {
            when (it) {
                RegisterViewModel.RegisterState.EmailError -> {
                    // Show error on email field
                }
                RegisterViewModel.RegisterState.PasswordError -> {
                    // Show error on pass field
                }
                RegisterViewModel.RegisterState.ConfirmPasswordError -> {
                    // Show error on confirm pass field
                }
                RegisterViewModel.RegisterState.BothError -> {
                    // Show error on both fields
                }
                RegisterViewModel.RegisterState.None -> {
                    // Hide error on all fields
                }
                RegisterViewModel.RegisterState.UserAlreadyExistsError -> {
                    // Show user already exists error
                }
                RegisterViewModel.RegisterState.Success -> {
                    // Registration successful
                }
            }
        }
    }

}
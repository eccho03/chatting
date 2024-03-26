package com.example.chatting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.chatting.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // 닉네임이 공백이 아닌 경우만 main activity로 전환이 이루어지도록 함
        binding.etNickname.addTextChangedListener {text ->
            binding.btnLogin.isEnabled = text.toString().replace(" ", "") != ""

        }
        binding.btnLogin.setOnClickListener {
            val nickname = binding.etNickname.text.toString()
            val bundle = Bundle()
            bundle.putString("nickname", nickname)
            // chat fragment로 이동
            (activity as MainActivity).replaceFragment(bundle)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
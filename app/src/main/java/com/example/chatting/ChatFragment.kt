package com.example.chatting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatting.databinding.FragmentChatBinding

class ChatFragment: Fragment() {
    lateinit var binding: FragmentChatBinding
    private lateinit var curUser: String    // 현재 로그인한 유저
    private lateinit var registeration: ListenRegisteration  // 문서 수신
    private val chatList = arrayListOf<ChatLayout>() // 리사이클러 뷰 목록
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // LoginFragment에서 입력한 닉네임 받아오기
        arguments?.let {
            curUser = it.getString("nickname").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        Toast.makeText(context, "${curUser}로 로그인 하셨습니다", Toast.LENGTH_SHORT).show()

        // 리사이클러뷰 설정
        binding.rvChatList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = ChatAdapter(curUser, chatList)
        binding.rvChatList.adapter = adapter

        binding.etMessage.addTextChangedListener { text ->
            binding.btnSend.isEnabled = text.toString() != ""
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
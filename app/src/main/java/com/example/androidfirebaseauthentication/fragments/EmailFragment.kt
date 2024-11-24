package com.example.androidfirebaseauthentication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidfirebaseauthentication.databinding.FragmentEmailBinding
import com.example.androidfirebaseauthentication.modal.EmailMessage
import com.example.androidfirebaseauthentication.service.RecyclerAdapter

class EmailFragment : Fragment() {
    private var _binding: FragmentEmailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = arguments?.getString("email")
        binding.toolbar.setTitle(email)
        val messages = arrayListOf(
            EmailMessage("first", "one", "12345"),
            EmailMessage("second", "two", "54321")
        )
        binding.recyclerRV.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecyclerAdapter(messages)
        binding.recyclerRV.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
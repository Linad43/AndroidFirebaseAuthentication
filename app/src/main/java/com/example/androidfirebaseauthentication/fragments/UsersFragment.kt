package com.example.androidfirebaseauthentication.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidfirebaseauthentication.R
import com.example.androidfirebaseauthentication.databinding.FragmentUsersBinding
import com.example.androidfirebaseauthentication.modal.User
import com.example.androidfirebaseauthentication.service.RecyclerAdapter
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class UsersFragment : Fragment() {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private var adapter: RecyclerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun addUser(userAdd: User) {
        val id = FirebaseAuth.getInstance().currentUser!!.uid
        val database = Firebase.database.reference
            .child("users")
            .child(id)
        val map: HashMap<String, User> = HashMap()
        map[userAdd.name.toString()] = userAdd
        database.updateChildren(map as Map<String, Any>)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val email = arguments?.getString("email")
        val email = arguments?.getString("email")
        binding.toolbar.setTitle(email)
        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.exit -> requireActivity().finishAffinity()
            }
            true
        }
        binding.recyclerRV.layoutManager = LinearLayoutManager(requireContext())
//        adapter = RecyclerAdapter()
//        binding.recyclerRV.adapter = adapter
        readUsers()
        binding.addBTN.setOnClickListener {
            val name = binding.nameET.text.toString()
            val numPhone = binding.numPhoneET.text.toString()
            if (name.isBlank() || numPhone.isBlank()) return@setOnClickListener
            val userAdd = User(name, numPhone)
            addUser(userAdd)
            with(binding) {
                nameET.text.clear()
                numPhoneET.text.clear()
            }
            readUsers()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun readUsers(){
        val id = FirebaseAuth.getInstance().currentUser!!.uid
        Firebase.database.reference.child("users")
            .child(id).get().addOnSuccessListener {
                val listUser = mutableListOf<User>()
                for(element in it.children){
                    val user = element.getValue(User::class.java)!!
                    listUser.add(user)
                }
                adapter = RecyclerAdapter(listUser)
                binding.recyclerRV.adapter = adapter
                adapter!!.notifyDataSetChanged()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
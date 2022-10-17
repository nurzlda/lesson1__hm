package com.example.lesson1_month4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lesson1_month4.databinding.FragmentAccountRegistrationBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AccountRegistrationFragment : Fragment() {

    lateinit var binding: FragmentAccountRegistrationBinding

    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentAccountRegistrationBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClicker()

    }

    private fun initClicker() {
       binding.btn.setOnClickListener {
           val name = binding.accountEdt.text.toString()
           val password = binding.passwordEdt.text.toString()

           if (name.isEmpty()){
               binding.accountEdt.error = "Заполните строку"
           }else if (password.isEmpty()){
               binding.passwordEdt.error = "Заполните строку"
           }else {
               val user = ProfileModel(accountName = name, password = password)
               db.collection("data").add(user).addOnSuccessListener {
                   Log.e("ololo", "initClicker: success ")
               }.addOnFailureListener {
                   Log.e("ololo", "initClicker: ${it.message}")
               }
               findNavController().navigate(R.id.aveFragment)
           }
       }
    }
}
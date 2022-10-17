package com.example.lesson1_month4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lesson1_month4.databinding.FragmentAveBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AveFragment : Fragment() {

    private lateinit var binding: FragmentAveBinding
    private val db = Firebase.firestore




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentAveBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {

            findNavController().navigate(R.id.homeFragment)
        }

        val list = arrayListOf<ProfileModel>()
        db.collection("data")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                      val data = document.data
                  list.add(ProfileModel(accountName = data["accountName"].toString()))
                }
                binding.textViewName.text = db.toString()
            }
            .addOnFailureListener { exception ->
                Log.w("ololo", "Error getting documents.", exception)
            }
    }

}
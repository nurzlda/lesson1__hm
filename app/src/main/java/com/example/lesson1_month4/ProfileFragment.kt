package com.example.lesson1_month4

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.lesson1_month4.databinding.FragmentProfileBinding
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException
import java.util.*
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val storageReference = FirebaseStorage.getInstance().reference

    lateinit var prefernces: SharedPreferences
//    val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        prefernces = requireActivity().getSharedPreferences("APP", Context.MODE_PRIVATE)
        return binding.root

    }

    companion object {
        const val IMAGE_REQUEST_CODE = 100
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).binding.titleTv.text = "Профиль"
        (requireActivity() as MainActivity).binding.profileImage.setImageResource(R.drawable.ic_task)
        (requireActivity() as MainActivity).binding.profileImage.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }
        binding.imageViewProfile.setOnClickListener {
            pickImageGallery()
        }

    }


    private fun pickImageGallery() {
        binding.imageViewProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_REQUEST_CODE)
        }
        val uriString = prefernces.getString("image_uri", "")
        if (uriString != null) {
            binding.imageViewProfile.load(uriString)
        }

    }

    private fun uploadImage(filePath: Uri?) {
        if (filePath != null) {
            val ref = storageReference.child("myImages/" + UUID.randomUUID().toString())
            ref.putFile(filePath)
        } else {
            Toast.makeText(requireContext(), "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            val filePath = data?.data
            try {
                val bitmap =
                        MediaStore.Images.Media.getBitmap(
                            requireActivity().contentResolver,
                            filePath)
                    binding.imageViewProfile.setImageBitmap(bitmap)
                    uploadImage(filePath)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
        }
    }
}

package com.example.lesson1_month4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.lesson1_month4.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var taskAdapter = TaskAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()
        (requireActivity() as MainActivity).showToolBar()
        (requireActivity() as MainActivity).binding.titleTv.text = "Задачи"
        (requireActivity() as MainActivity).binding.profileImage.load(R.drawable.ic_person)
        arguments?.let {
            val list = arrayListOf<TaskModel>()
            val model = it.getSerializable("model") as TaskModel
            list.add(model)
            taskAdapter = TaskAdapter(list)
            binding.recyclerView.adapter = taskAdapter
        }

    }
    private fun initClicker() {
        binding.createFab.setOnClickListener{
            CreateTaskFragment().show(requireActivity().supportFragmentManager, "")

        }
        (requireActivity() as MainActivity).binding.profileImage.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }


    }

}
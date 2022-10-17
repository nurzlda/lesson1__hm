package com.example.lesson1_month4

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lesson1_month4.databinding.FragmentBoardBinding


class BoardFragment : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).hideToolBar()
          val preferences = requireActivity().getSharedPreferences("setting", Context.MODE_PRIVATE)
            val isShow : Boolean  = preferences.getBoolean( "isShow", false)
            if (isShow){
               findNavController().navigate(R.id.startRegistrationFragment)
            }



        val list = arrayListOf<BoardModel>()
        list.add(BoardModel("first_anm.json", "Экономь время", "дальше"))
        list.add(BoardModel("second_anm.json", "Достигай целей", "дальше"))
        list.add(BoardModel("third_anm.json", "Развивайся", "начинаем"))
        val boardAdapter = BoardAdapter(list, this)
        binding.viewPager.adapter = boardAdapter
        binding.dotsIndicator.attachTo(binding.viewPager)
    }




    override fun itemClick() {
        val preferences = requireActivity().getSharedPreferences("setting", Context.MODE_PRIVATE)
        preferences.edit().putBoolean("isShow", true).apply()
        findNavController().navigate(R.id.startRegistrationFragment)
    }

    override fun itemClick2() {
        btnNext()
    }


    private fun btnNext(){
        binding.viewPager.setCurrentItem(getItemOfViewPager(+1), true)
    }


    private fun getItemOfViewPager(i: Int): Int {
        return binding.viewPager.currentItem + i
    }
}
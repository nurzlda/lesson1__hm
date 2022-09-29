package com.example.lesson1_month4

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.lesson1_month4.databinding.FragmentBoardBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class BoardFragment : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).hideToolBar()
        val preferences = requireActivity().getSharedPreferences("setting", Context.MODE_PRIVATE)
        val isShow : Boolean  = preferences.getBoolean("isShow", false)
        if (isShow){
            findNavController().navigate(R.id.action_boardFragment_to_homeFragment)
        }



        val list = arrayListOf<BoardModel>()
        list.add(BoardModel(R.drawable.board_first, "Экономь время", "дальше"))
        list.add(BoardModel(R.drawable.board_second, "Достигай целей", "дальше"))
        list.add(BoardModel(R.drawable.board_third, "Развивайся", "начинаем"))
        val boardAdapter = BoardAdapter(list, this)
        binding.viewPager.adapter = boardAdapter
        binding.dotsIndicator.attachTo(binding.viewPager)
    }




    override fun itemClick() {
        val preferences = requireActivity().getSharedPreferences("setting", Context.MODE_PRIVATE)
        preferences.edit().putBoolean("isShow", true).apply()
        findNavController().navigate(R.id.action_boardFragment_to_homeFragment)
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
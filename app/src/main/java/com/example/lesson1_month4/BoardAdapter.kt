package com.example.lesson1_month4

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import coil.load
import com.example.lesson1_month4.databinding.ItemBoardBinding


class BoardAdapter(

    private val list: ArrayList<BoardModel>,
    private val listener: ItemClickListener) :
    RecyclerView.Adapter<BoardAdapter.BoardViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
      return BoardViewHolder(ItemBoardBinding.inflate(LayoutInflater.from(parent.context),
          parent,false))

    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        return holder.onBind(list[position])

    }


    override fun getItemCount(): Int {
      return list.size
    }

    inner class BoardViewHolder(private val binding:ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(boardModel: BoardModel) {
            binding.imageView.load(boardModel.image)
            binding.nxtBtn.text = boardModel.button
            binding.tvDescription.text = boardModel.description

            binding.nxtBtn.setOnClickListener {
                if (adapterPosition == list.size -1) {
                    listener.itemClick()
                }else {
                    listener.itemClick2()
                }
            }

          }

        }
    }
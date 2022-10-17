package com.example.lesson1_month4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1_month4.databinding.TaskItemBinding
import com.example.lesson1_month4.room.TaskModel

class TaskAdapter(private val list: List<TaskModel> , private var listener: TaskClickListener) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
       return list.size
    }


    inner class TaskViewHolder(private val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(taskModel: TaskModel) {
            binding.tvTitle.text = taskModel.task
            binding.tvDate.text = taskModel.date
            binding.tvRegular.text = taskModel.regular
            itemView.setOnClickListener {
                listener.itemClick(taskModel)
            }
            itemView.setOnLongClickListener {
                listener.deleteItemClick(taskModel)
                false
            }


        }

    }
}
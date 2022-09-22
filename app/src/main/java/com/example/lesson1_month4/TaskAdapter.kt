package com.example.lesson1_month4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1_month4.databinding.TaskItemBinding

class TaskAdapter(private val list: ArrayList<TaskModel>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

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


    class TaskViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(taskModel: TaskModel) {
            binding.tvTitle.text = taskModel.task
            binding.tvDate.text = taskModel.date
            binding.tvRegular.text = taskModel.regular

        }

    }
}
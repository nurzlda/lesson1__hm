package com.example.lesson1_month4

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.navigation.fragment.findNavController
import com.example.lesson1_month4.databinding.FragmentCreateTaskBinding
import com.example.lesson1_month4.databinding.RegularDialogBinding
import com.example.lesson1_month4.room.TaskModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*


class CreateTaskFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentCreateTaskBinding

    var task = ""
    var date = ""
    var regular = ""
    var taskModel : TaskModel? = null

    val db = Firebase.firestore



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()
        if (tag == "update"){
            arguments?.let {
                taskModel = it.getSerializable("model")as TaskModel

                binding.edtTask.setText(taskModel!!.task)
                binding.dateBtn.text = taskModel!!.date
                binding.regularBtn.text = taskModel!!.regular
            }
        }
    }

    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = RegularDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.show()
        binding.everyDayBtn.setOnClickListener {
          regular = binding.everyDayBtn.text.toString()
          this.binding.regularBtn.text = regular
          dialog.dismiss()
      }
        binding.everyWeekBtn.setOnClickListener {
            regular = binding.everyWeekBtn.text.toString()
            this.binding.regularBtn.text = regular
            dialog.dismiss()
        }
        binding.everyMonthBtn.setOnClickListener {
            regular = binding.everyMonthBtn.text.toString()
            this.binding.regularBtn.text = regular
            dialog.dismiss()
        }
        binding.everyYearBtn.setOnClickListener {
            regular = binding.everyYearBtn.text.toString()
            this.binding.regularBtn.text = regular
            dialog.dismiss()
        }
        binding.cancel.setOnClickListener {
            dialog.dismiss()
        }
    }




    @SuppressLint("SetTextI18n")
    private fun initClicker() {
      with(binding){

          applyBtn.setOnClickListener {

              if (tag == "update"){
                  val model =
                      TaskModel(
                          id= taskModel!!.id,
                          task=edtTask.text.toString(),
                          date = dateBtn.text.toString(),
                          regular = regularBtn.text.toString())

                  App.appDataBase.taskDao().update(model)
              } else {
                  val model = TaskModel(task=edtTask.text.toString(), date = date, regular = regular)
                  App.appDataBase.taskDao().insert(model)
                  db.collection("tasks").add(model).addOnSuccessListener {
                      Log.e("ololo", "initClicker: success " )
                  }.addOnFailureListener {
                      Log.e("ololo", "initClicker: ${it.message}" )
                  }
              }

              dismiss()
              findNavController().navigate(R.id.homeFragment)
          }

          regularBtn.setOnClickListener {
              showDialog() }

          dateBtn.setOnClickListener {

              val c = Calendar.getInstance()
              val year = c.get(Calendar.YEAR)
              val month = c.get(Calendar.MONTH)
              val day = c.get(Calendar.DAY_OF_MONTH)


              val dpd = DatePickerDialog(requireContext(), R.style.DialogTheme, DatePickerDialog.OnDateSetListener
              {  view, year, monthOfYear, dayOfMonth ->

                  dateBtn.text = "$dayOfMonth.${monthOfYear+1}.$year"
                  date = dateBtn.text.toString()

              }, year, month, day)
              dpd.show()
          }
      }
    }

}
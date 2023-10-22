package com.example.taskdemo.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskdemo.databinding.EachRowBinding

class DataAdapter(private val dataModel: DataModel) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    inner class DataViewHolder(
        binding:
    EachRowBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = EachRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int = 0


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

    }
}
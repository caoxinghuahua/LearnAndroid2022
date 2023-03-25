package com.example.kotlinleran

import android.content.ClipData
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var context: Context, var data: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType % 2 == 0) {
            var view = View.inflate(context, R.layout.layout_a, null)
            return AViewHolder(view)
        } else {
            var view = View.inflate(context, R.layout.layout_b, null)
            return BViewHolder(view)
        }



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class AViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    class BViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }
}
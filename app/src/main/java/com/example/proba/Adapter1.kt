package com.example.proba

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter1(private val numbers: List<Int>) :
    RecyclerView.Adapter<Adapter1.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var text1: TextView? = null

        val text1: TextView = itemView.findViewById(R.id.text_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycle_item1, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {
        val chislo = getitem(pos)
       holder.text1.text= chislo.toString()
        if (chislo % 2 == 0) {
            holder.text1.textSize = 100.01f
        } else {
            holder.text1.textSize = 50f

        }
    }

    override fun getItemCount(): Int {
        return numbers.size
    }

    fun getitem(position: Int):Int{
        return numbers.getOrNull(position)?:-1 //?:-1 перестраховка от сбоев
    }
}
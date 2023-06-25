package com.example.funds0.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.funds0.R
import com.example.funds0.models.fundsmodel

class FndviewAdapter(private val fundslist:ArrayList<fundsmodel>): RecyclerView.Adapter<FndviewAdapter.ViewHolder>() {
    private lateinit var myListner: onItemClickLstner
    interface onItemClickLstner{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListner(clickLstner: onItemClickLstner){
        myListner=clickLstner
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val fundview= LayoutInflater.from(parent.context).inflate(R.layout.activity_itemdonor,parent,false)
        return ViewHolder(fundview,myListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentfund=fundslist[position]
        holder.rvfun.text=currentfund.accountname

    }




    override fun getItemCount(): Int {
        return fundslist.size
    }

    class ViewHolder(itemView: View, clickLstner: onItemClickLstner): RecyclerView.ViewHolder(itemView) {
        val rvfun: TextView =itemView.findViewById(R.id.rvfun)

        init{
            itemView.setOnClickListener{
                clickLstner.onItemClick(adapterPosition)
            }
        }

    }

}
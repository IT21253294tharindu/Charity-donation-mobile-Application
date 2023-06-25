package com.example.funds0.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.funds0.R
import com.example.funds0.models.donatemodel

data class donAdapter (private val donatelist:ArrayList<donatemodel>): RecyclerView.Adapter<donAdapter.ViewHolder>() {
     private lateinit var myListner: onItemClickLstner
     interface onItemClickLstner{
         fun onItemClick(position: Int)
     }
     fun setOnItemClickListner(clickLstner: onItemClickLstner){
         myListner=clickLstner
     }
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

         val donview= LayoutInflater.from(parent.context).inflate(R.layout.activity_donateitem,parent,false)
         return ViewHolder(donview,myListner)
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val currentfund=donatelist[position]
         holder.rvdon.text=currentfund.donid
     }




     override fun getItemCount(): Int {
         return donatelist.size
     }

     class ViewHolder(itemView: View, clickLstner: onItemClickLstner): RecyclerView.ViewHolder(itemView) {
         val rvdon: TextView =itemView.findViewById(R.id.rvdon)

         init{
             itemView.setOnClickListener{
                 clickLstner.onItemClick(adapterPosition)
             }
         }

     }

 }



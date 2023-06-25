package com.example.funds0.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.funds0.R
import com.example.funds0.models.fundsmodel

class fundsAdapter (private val fundslist:ArrayList<fundsmodel>):RecyclerView.Adapter<fundsAdapter.ViewHolder>(){
    private lateinit var mListner: onItemClickLstner
    interface onItemClickLstner{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListner(clickLstner: onItemClickLstner){
        mListner=clickLstner
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val fundview=LayoutInflater.from(parent.context).inflate(R.layout.activity_displayrequestfund,parent,false)
        return ViewHolder(fundview,mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentfund=fundslist[position]
        holder.rvtfund.text=currentfund.accountname
    }


    override fun getItemCount(): Int {
        return fundslist.size
    }

    class ViewHolder( itemView: View,clickLstner: onItemClickLstner): RecyclerView.ViewHolder(itemView) {
           val rvtfund:TextView=itemView.findViewById(R.id.rvtfund)

        init{
            itemView.setOnClickListener{
                clickLstner.onItemClick(adapterPosition)
            }
        }

    }

}
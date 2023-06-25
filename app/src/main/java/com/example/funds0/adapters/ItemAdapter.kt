package com.example.funds0.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.funds0.models.itemmodel
import com.example.funds0.R

class itemAdapter (private val itemlist:ArrayList<itemmodel>):RecyclerView.Adapter<itemAdapter.ViewHolder>(){
    private lateinit var mListner: onItemClickLstner
    interface onItemClickLstner{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListner(clickLstner: onItemClickLstner){
        mListner=clickLstner
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemview=LayoutInflater.from(parent.context).inflate(R.layout.activity_item_item,parent,false)
        return ViewHolder(itemview,mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentitem=itemlist[position]
        holder.rvreqt.text=currentitem.itemid

    }


    override fun getItemCount(): Int {
        return itemlist.size
    }

    class ViewHolder( itemView: View,clickLstner: onItemClickLstner): RecyclerView.ViewHolder(itemView) {
        val rvreqt:TextView=itemView.findViewById(R.id.rvreqt)

        init{
            itemView.setOnClickListener{
                clickLstner.onItemClick(adapterPosition)
            }
        }

    }

}
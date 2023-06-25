package com.example.funds0.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.funds0.models.CharityModel
import com.example.funds0.R


class CharityAdapter(private val cList: ArrayList<CharityModel>) :
    RecyclerView.Adapter<CharityAdapter.ViewHolder>(){

    private lateinit var  cListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        cListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.emp_list_item,parent,false)
        return ViewHolder(itemView, cListener)
    }

    override fun getItemCount(): Int {
        return cList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCharity = cList[position]
        holder.tvCName.text = currentCharity.charityName
    }

    class ViewHolder(itemView: View,clickListener: onItemClickListener):RecyclerView.ViewHolder(itemView) {

        val tvCName : TextView = itemView.findViewById(R.id.charityUName)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }

    }



}
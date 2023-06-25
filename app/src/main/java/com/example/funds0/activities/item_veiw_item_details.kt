package com.example.funds0.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.funds0.R

class item_veiw_item_details : AppCompatActivity() {
    private lateinit var ctype: TextView
    private lateinit var mtype: TextView
    private lateinit var ftype: TextView
    private lateinit var etype: TextView
    private lateinit var qc: TextView
    private lateinit var qm: TextView
    private lateinit var qf: TextView
    private lateinit var qe: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_veiw_item_details)

       initview()
        setviewvalues()


    }

    private fun initview(){
        ctype=findViewById(R.id.ctype)
        mtype=findViewById(R.id.mtype)
        ftype=findViewById(R.id.ftype)
        etype=findViewById(R.id.etype)
        qc=findViewById(R.id.qc)
        qm=findViewById(R.id.qm)
        qf=findViewById(R.id.qf)
        qe=findViewById(R.id.qe)
    }
    private fun setviewvalues(){
        ctype.text=intent.getStringExtra("clothetype")
        mtype.text=intent.getStringExtra("medicaltype")
        ftype.text=intent.getStringExtra("foodtype")
        etype.text=intent.getStringExtra("educationtype")
        qc.text=intent.getStringExtra("clothequantity")
        qm.text=intent.getStringExtra("medicalquantity")
        qf.text=intent.getStringExtra("foodquantity")
        qe.text=intent.getStringExtra("educationquantity")
    }
}
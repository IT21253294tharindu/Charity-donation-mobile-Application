
package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.funds0.R
import com.example.funds0.adapters.fundsAdapter
import com.example.funds0.models.fundsmodel
import com.google.firebase.database.*

class request_history : AppCompatActivity() {
    private lateinit var rvfunds:RecyclerView
    private lateinit var tvloadingData:TextView
    private lateinit var fundlist:ArrayList<fundsmodel>
    private lateinit var dbRef:DatabaseReference
    private lateinit var btndonator:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_history)

        rvfunds=findViewById(R.id.rvfunds)
        rvfunds.layoutManager=LinearLayoutManager(this)
        rvfunds.setHasFixedSize(true)
        tvloadingData=findViewById(R.id.tvloadingData)

        fundlist= arrayListOf<fundsmodel>()
        getFundsData()

        btndonator=findViewById(R.id.btndonator)

        btndonator.setOnClickListener{
            val intent=Intent(this, donorHomepage::class.java)
            startActivity(intent)
        }


    }
    private fun getFundsData(){
        rvfunds.visibility= View.GONE
        tvloadingData.visibility=View.VISIBLE

         dbRef=FirebaseDatabase.getInstance().getReference("funds")

        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                fundlist.clear()
                if(snapshot.exists()){
                    for(fundsnap in snapshot.children){
                        val funddata=fundsnap.getValue(fundsmodel::class.java)
                        fundlist.add(funddata!!)
                    }
                    val madapter= fundsAdapter(fundlist)
                    rvfunds.adapter=madapter
                    madapter.setOnItemClickListner(object : fundsAdapter.onItemClickLstner {
                        override fun onItemClick(position: Int) {
                            val intent=Intent(this@request_history, displayrequestedfundDetails::class.java)
                            //put extra
                            intent.putExtra("fundid",fundlist[position].fundid)
                            intent.putExtra("accountname",fundlist[position].accountname)
                            intent.putExtra("accountno",fundlist[position].accountno)
                            intent.putExtra("bankname",fundlist[position].bank)
                            intent.putExtra("clothesamount",fundlist[position].clothesamount)
                            intent.putExtra("medicalsamount",fundlist[position].medicalsamount)
                            intent.putExtra("foodsamount",fundlist[position].foodsamount)
                            intent.putExtra("educationsamount",fundlist[position].educationsamount)
                            startActivity(intent)

                        }

                    })

                    rvfunds.visibility=View.VISIBLE
                    tvloadingData.visibility=View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
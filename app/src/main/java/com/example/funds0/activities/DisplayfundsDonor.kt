package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.funds0.adapters.FndviewAdapter
import com.example.funds0.R
import com.example.funds0.models.fundsmodel
import com.google.firebase.database.*

class DisplayfundsDonor : AppCompatActivity() {
    private lateinit var recyclerView1:RecyclerView
    private lateinit var  tvloadingData: TextView
    private lateinit var fundlist:ArrayList<fundsmodel>
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displayfunds_donor)

        recyclerView1=findViewById(R.id.recyclerView1)
        recyclerView1.layoutManager= LinearLayoutManager(this)
        recyclerView1.setHasFixedSize(true)
        tvloadingData=findViewById(R.id.tvloadingData1)

        fundlist= arrayListOf<fundsmodel>()
        getFundsData()
    }
    private fun getFundsData(){
        recyclerView1.visibility= View.GONE
        tvloadingData.visibility= View.VISIBLE

        dbRef= FirebaseDatabase.getInstance().getReference("funds")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                fundlist.clear()
                if(snapshot.exists()){
                    for(fundsnap in snapshot.children){
                        val funddata=fundsnap.getValue(fundsmodel::class.java)
                        fundlist.add(funddata!!)
                    }
                    val madapter= FndviewAdapter(fundlist)
                    recyclerView1.adapter=madapter
                    madapter.setOnItemClickListner(object : FndviewAdapter.onItemClickLstner {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@DisplayfundsDonor, viewFundDetails::class.java)
                            //put extra
                            intent.putExtra("fundid", fundlist[position].fundid)
                            intent.putExtra("accountname", fundlist[position].accountname)
                            intent.putExtra("accountno", fundlist[position].accountno)
                            intent.putExtra("bankname", fundlist[position].bank)
                            intent.putExtra("clothesamount", fundlist[position].clothesamount)
                            intent.putExtra("medicalsamount", fundlist[position].medicalsamount)
                            intent.putExtra("foodsamount", fundlist[position].foodsamount)
                            intent.putExtra("educationsamount", fundlist[position].educationsamount)
                            startActivity(intent)


                        }
                    })
                    recyclerView1.visibility= View.VISIBLE
                    tvloadingData.visibility= View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
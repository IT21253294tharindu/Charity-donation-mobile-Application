package com.example.funds0.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.funds0.R
import com.example.funds0.adapters.donAdapter
import com.example.funds0.models.donatemodel
import com.google.firebase.database.*

class donatedhistory : AppCompatActivity() {
    private lateinit var rvdonate:RecyclerView
    private lateinit var tvloadingData: TextView
    private lateinit var donatelist:ArrayList<donatemodel>
    private lateinit var dbRef: DatabaseReference


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donatedhistory)

        rvdonate=findViewById(R.id.rvdonate)
        rvdonate.layoutManager= LinearLayoutManager(this)
        rvdonate.setHasFixedSize(true)
        tvloadingData=findViewById(R.id.tvloadingData)

       donatelist= arrayListOf<donatemodel>()
        getdonatesData()
    }

    private fun getdonatesData(){
        rvdonate.visibility= View.GONE
        tvloadingData.visibility= View.VISIBLE

        dbRef= FirebaseDatabase.getInstance().getReference("donates")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                donatelist.clear()
                if(snapshot.exists()){
                    for(donatessnap in snapshot.children){
                        val donatedata=donatessnap.getValue(donatemodel::class.java)
                        donatelist.add(donatedata!!)
                    }
                    val madapter= donAdapter(donatelist)
                    rvdonate.adapter=madapter
                    madapter.setOnItemClickListner(object : donAdapter.onItemClickLstner {
                        override fun onItemClick(position: Int) {
                            val intent= Intent(this@donatedhistory, donationdetails::class.java)
                            //put extra
                            intent.putExtra("fundid",donatelist[position].donid)
                            intent.putExtra("accountname",donatelist[position].accountname)
                            intent.putExtra("accountno",donatelist[position].accountno)
                            intent.putExtra("bankname",donatelist[position].bank)
                            intent.putExtra("clothesamount",donatelist[position].clothesamount)
                            intent.putExtra("medicalsamount",donatelist[position].medicalsamount)
                            intent.putExtra("foodsamount",donatelist[position].foodsamount)
                            intent.putExtra("educationsamount",donatelist[position].educationsamount)
                            startActivity(intent)

                        }

                    })

                    rvdonate.visibility= View.VISIBLE
                    tvloadingData.visibility= View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
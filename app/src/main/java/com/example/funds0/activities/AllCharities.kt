package com.example.funds0.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.funds0.R
import com.example.funds0.adapters.CharityAdapter
import com.example.funds0.models.CharityModel
import com.example.funds0.activities.Charity_Profile
import com.google.firebase.database.*

class AllCharities : AppCompatActivity() {
    private lateinit var cRecyclerView :  RecyclerView
    private lateinit var tvLoadingDat : TextView
    private lateinit var charityList : ArrayList<CharityModel>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_charities)

        cRecyclerView = findViewById(R.id.rvCharity)
        cRecyclerView.layoutManager = LinearLayoutManager(this)
        cRecyclerView.setHasFixedSize(true)
        tvLoadingDat = findViewById(R.id.tvLoadingData)

        charityList = arrayListOf<CharityModel>()

        getCharitiesData()

    }

    private fun getCharitiesData(){
        cRecyclerView.visibility = View.GONE
        tvLoadingDat.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("CharityInfo")

        dbRef.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
               charityList.clear()
                if(snapshot.exists()){
                    for(CSnap in snapshot.children){
                        val CharityData = CSnap.getValue(CharityModel::class.java)
                        charityList.add(CharityData!!)
                    }
                    val cAdapter = CharityAdapter(charityList)
                    cRecyclerView.adapter = cAdapter

                    cAdapter.setOnItemClickListener(object : CharityAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@AllCharities, Charity_Profile::class.java)
                            //put extras
                            intent.putExtra("cId", charityList[position].cId)
                            intent.putExtra("CharityVold", charityList[position].CharityVold)
                            intent.putExtra("charityName", charityList[position].charityName)
                            intent.putExtra("charityOgName", charityList[position].charityOgName)
                            intent.putExtra("CharityEmail", charityList[position].CharityEmail)
                            intent.putExtra("CharityAddress", charityList[position].CharityAddress)
                            intent.putExtra("CharityTelNo", charityList[position].CharityTelNo)
                            intent.putExtra("CharityPersonalNo", charityList[position].CharityPersonalNo)
                            intent.putExtra("CharityWeb", charityList[position].CharityWeb)
                            intent.putExtra("CharityPass", charityList[position].CharityPass)
                            startActivity(intent)
                        }

                    })

                    cRecyclerView.visibility = View.VISIBLE
                    tvLoadingDat.visibility = View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}
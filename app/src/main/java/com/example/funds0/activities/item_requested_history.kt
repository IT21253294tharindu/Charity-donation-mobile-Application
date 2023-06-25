package com.example.funds0.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.funds0.adapters.itemAdapter
import com.example.funds0.R
import com.example.funds0.models.itemmodel
import com.google.firebase.database.*

class item_requested_history : AppCompatActivity() {
    private lateinit var rvitem: RecyclerView
    private lateinit var itemlist:ArrayList<itemmodel>
    private lateinit var tvloadingData: TextView
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_requested_history)
        rvitem=findViewById(R.id.rvitem)
        rvitem.layoutManager= LinearLayoutManager(this)
        rvitem.setHasFixedSize(true)
        tvloadingData=findViewById(R.id.tvloadingData)

        itemlist= arrayListOf<itemmodel>()
        getitemData()



    }
    private fun getitemData(){
        rvitem.visibility= View.GONE
        tvloadingData.visibility= View.VISIBLE

        dbRef= FirebaseDatabase.getInstance().getReference("items")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                itemlist.clear()
                if(snapshot.exists()){
                    for(fundsnap in snapshot.children){
                        val itemdata=fundsnap.getValue(itemmodel::class.java)
                        itemlist.add(itemdata!!)
                    }
                    val madapter= itemAdapter(itemlist)
                   rvitem.adapter=madapter
                    madapter.setOnItemClickListner(object : itemAdapter.onItemClickLstner {
                        override fun onItemClick(position: Int) {
                            val intent= Intent(
                                this@item_requested_history,
                                item_veiw_item_details::class.java
                            )
                            //put extra
                            intent.putExtra("itemid",itemlist[position].itemid)
                            intent.putExtra("clothename",itemlist[position].clothename)
                            intent.putExtra("clothetype",itemlist[position].clothetype)
                            intent.putExtra("clothequantity",itemlist[position].clothquantity)
                            intent.putExtra("medicalname",itemlist[position].medicalname)
                            intent.putExtra("medicaltype",itemlist[position].medicaltype)
                            intent.putExtra("medicalquantity",itemlist[position].medicalquantity)
                            intent.putExtra("foodname",itemlist[position].foodname)
                            intent.putExtra("foodtype",itemlist[position].foodtype)
                            intent.putExtra("foodquantity",itemlist[position].foodquantity)
                            intent.putExtra("educationname",itemlist[position].educationname)
                            intent.putExtra("educationtype",itemlist[position].educationtype)
                            intent.putExtra("educationquantity",itemlist[position].educationquantity)
                            startActivity(intent)

                        }

                    })

                    rvitem.visibility= View.VISIBLE
                    tvloadingData.visibility= View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
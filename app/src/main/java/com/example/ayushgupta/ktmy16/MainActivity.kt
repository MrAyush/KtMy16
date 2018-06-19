package com.example.ayushgupta.ktmy16

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import com.example.ayushgupta.ktmy16.bean.ArrivalPojo
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit

class MainActivity : AppCompatActivity() {

    var lview: ListView? = null
    var et1: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lview = findViewById(R.id.lview)
        et1 = findViewById(R.id.et1)
    }

    fun getTrainArrival(v: View) {
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.railwayapi.com/").build()
        val arrival = retrofit.create(Arrival::class.java)
        val trainArrival = arrival.getTrainArrival(et1?.text.toString())
        trainArrival.enqueue(object : Callback<ArrivalPojo> {
            override fun onFailure(t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(response: Response<ArrivalPojo>?, retrofit: Retrofit?) {
                val status = response?.body()
                val list = mutableListOf<String>()
                val train = status?.trains
                for (t in train!!) {
                    list.add("${t.name} ${t.number}")
                }
                val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, list)
                lview?.adapter = adapter
            }

        })
    }
}

package com.example.ayushgupta.ktmy16

import com.example.ayushgupta.ktmy16.bean.ArrivalPojo
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Path

interface Arrival {
    @GET("v2/arrivals/station/{stncode}/hours/2/apikey/087exesfeq/")
    fun getTrainArrival(@Path("stncode")s:String): Call<ArrivalPojo>
}
package com.example.ayushgupta.ktmy16.bean

data class ArrivalPojo(
        val trains: List<Train>,
        val debit: Int,
        val response_code: Int,
        val total: Int
)
package com.example.ujian_akhir_pam.model

data class Kontak(
    val id: String,
    val nama: String,
    val waktupenyewaan: String,
    val telepon: String,
    val tanggal: String,
    val jam:String
){
    constructor(): this("","","","", "", "")
}
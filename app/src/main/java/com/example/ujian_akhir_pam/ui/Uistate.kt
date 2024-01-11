package com.example.ujian_akhir_pam.ui

import com.example.ujian_akhir_pam.model.Kontak

data class UIStateKontak(
    val detailKontak: DetailKontak= DetailKontak(),
)

data class  DetailKontak(
    val id: String = "",
    val nama : String= "",
    val waktupenyewaan : String= "",
    val telpon : String= "",
    val tanggal : String = "",
    val jam : String = ""
)

/* Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya*/
fun DetailKontak.toKontak(): Kontak = Kontak(
    id = id,
    nama = nama,
    waktupenyewaan = waktupenyewaan,
    telepon = telpon,
    tanggal = tanggal,
    jam = jam
)

data class DetailUIState(
    val addEvent: DetailKontak = DetailKontak(),
)

fun Kontak.toDetailKontak(): DetailKontak =
    DetailKontak(
        id = id,
        nama = nama,
        waktupenyewaan = waktupenyewaan,
        telpon = telepon,
        tanggal = tanggal,
        jam = jam
    )

fun Kontak.toUiStateKontak(): UIStateKontak = UIStateKontak(
    detailKontak = this.toDetailKontak()
)

data class HomeUIState(
    val listKontak: List<Kontak> = listOf(),
    val dataLength: Int = 0
)


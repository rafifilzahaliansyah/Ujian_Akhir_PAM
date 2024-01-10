package com.example.ujian_akhir_pam.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ujian_akhir_pam.data.KontakRepository
import com.example.ujian_akhir_pam.ui.DetailKontak
import com.example.ujian_akhir_pam.ui.UIStateKontak
import com.example.ujian_akhir_pam.ui.toKontak

class AddViewModel(private val kontakRepository: KontakRepository): ViewModel(){
    /**
     * Berisi status siswa saat ini
     */
    var uiStateKontak by mutableStateOf(UIStateKontak())
        private set

    /* Fungsi untuk memvalidasi input */

    fun updateUiState(detailKontak: DetailKontak) {
        uiStateKontak=
            UIStateKontak(detailKontak = detailKontak)
    }

    /*Fungsi untuk menyimpan data yang di-entry */
    suspend fun saveKontak(){
        kontakRepository.save(uiStateKontak.detailKontak.toKontak())
    }
}
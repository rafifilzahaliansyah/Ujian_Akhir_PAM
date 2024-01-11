package com.example.ujian_akhir_pam.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ujian_akhir_pam.KontakApplication
import com.example.ujian_akhir_pam.ui.add.AddViewModel
import com.example.ujian_akhir_pam.ui.detail.DetailViewModel
import com.example.ujian_akhir_pam.ui.edit.EditViewModel
import com.example.ujian_akhir_pam.ui.home.HomeViewModel

fun CreationExtras.aplikasiKontak():KontakApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KontakApplication)

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            AddViewModel(aplikasiKontak().container.kontakRepository)
        }

        initializer {
            HomeViewModel(aplikasiKontak().container.kontakRepository)
        }

        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiKontak().container.kontakRepository
            )
        }

        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiKontak().container.kontakRepository
            )
        }
    }
}
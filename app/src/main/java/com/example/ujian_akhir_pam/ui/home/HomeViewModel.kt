package com.example.ujian_akhir_pam.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujian_akhir_pam.data.KontakRepository
import com.example.ujian_akhir_pam.model.Kontak
import com.example.ujian_akhir_pam.ui.HomeUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class KontakUIState {
    data class Success(val kontak: Flow<List<Kontak>>) : KontakUIState()
    object Error : KontakUIState()
    object Loading : KontakUIState()
}

class HomeViewModel(private val kontakRepository: KontakRepository) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUIState: StateFlow<HomeUIState> = kontakRepository.getAll()
        .filterNotNull()
        .map {
            HomeUIState (listKontak = it.toList(), it.size ) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState()

        )

}
package com.example.ujian_akhir_pam.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujian_akhir_pam.data.KontakRepository
import com.example.ujian_akhir_pam.ui.DetailUIState
import com.example.ujian_akhir_pam.ui.toDetailKontak
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: KontakRepository
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val kontakId: String = checkNotNull(savedStateHandle[DetailDestination.kontakId])

    val uiState: StateFlow<DetailUIState> =
        repository.getKontakById(kontakId)
            .filterNotNull()
            .map {
                DetailUIState(addEvent = it.toDetailKontak())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )

    suspend fun deleteKontak() {
        repository.delete(kontakId)

    }


}
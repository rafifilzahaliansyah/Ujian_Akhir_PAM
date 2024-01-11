package com.example.ujian_akhir_pam.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujian_akhir_pam.data.KontakRepository
import com.example.ujian_akhir_pam.ui.DetailKontak
import com.example.ujian_akhir_pam.ui.UIStateKontak
import com.example.ujian_akhir_pam.ui.toKontak
import com.example.ujian_akhir_pam.ui.toUiStateKontak
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: KontakRepository
) : ViewModel() {

    var kontakUiState by mutableStateOf(UIStateKontak())
        private set

    private val kontakId: String = checkNotNull(savedStateHandle[EditDestination.kontakId])

    init {
        viewModelScope.launch {
            kontakUiState =
                repository.getKontakById(kontakId)
                    .filterNotNull()
                    .first()
                    .toUiStateKontak()
        }
    }

    fun updateUIState(detailKontak: DetailKontak) {
        kontakUiState = kontakUiState.copy(detailKontak = detailKontak)
    }

    suspend fun updateKontak() {
        repository.update(kontakUiState.detailKontak.toKontak())

    }
}
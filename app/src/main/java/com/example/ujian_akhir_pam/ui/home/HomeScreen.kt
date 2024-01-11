package com.example.ujian_akhir_pam.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ujian_akhir_pam.model.Kontak
import com.example.ujian_akhir_pam.navigation.DestinasiNavigasi
import com.example.ujian_akhir_pam.ui.KontakTopAppBar
import com.example.ujian_akhir_pam.ui.PenyediaViewModel

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Kontak"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            KontakTopAppBar(
                title = "Kontak",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        },
    ) { innerPadding ->
        val uiStateSiswa by viewModel.homeUIState.collectAsState()
        BodyHome(
            itemKontak = uiStateSiswa.listKontak,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onSiswaClick = onDetailClick
        )
    }
}

@Composable
fun BodyHome(
    itemKontak: List<Kontak>,
    modifier: Modifier = Modifier,
    onSiswaClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemKontak.isEmpty()) {
            Text(
                text = "Tidak ada data Kontak",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListKontak(
                itemKontak = itemKontak,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onSiswaClick(it.id) }
            )
        }
    }
}

@Composable
fun ListKontak(
    itemKontak: List<Kontak>,
    modifier: Modifier = Modifier,
    onItemClick: (Kontak) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemKontak, key = { it.id }) { kontak ->
            DataKontak(
                kontak = kontak,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(kontak) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataKontak(
    kontak: Kontak,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = kontak.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                )
                Text(
                    text = kontak.telepon,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = kontak.alamat,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
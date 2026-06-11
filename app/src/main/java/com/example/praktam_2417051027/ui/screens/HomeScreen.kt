package com.example.praktam_2417051027.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.praktam_2417051027.R
import com.example.praktam_2417051027.model.Makanan
import com.example.praktam_2417051027.model.UserAccount
import com.example.praktam_2417051027.ui.components.MenuCard

@Composable
fun HomeScreen(
    navController: NavController,
    user: UserAccount?,
    daftarMakanan: List<Makanan>,
    isLoadingData: Boolean,
    errorMessage: String?,
    onRefresh: () -> Unit
) {
    var kataKunci by remember { mutableStateOf("") }
    var hanyaMurah by remember { mutableStateOf(false) }
    var kategoriDipilih by remember { mutableStateOf("Semua") }

    val kategoriList = listOf("Semua", "Mie", "Dimsum", "Risoles")

    val makananTampil = daftarMakanan.filter { makanan ->
        val cocokSearch = makanan.nama.contains(kataKunci, ignoreCase = true) ||
                makanan.deskripsi.contains(kataKunci, ignoreCase = true)

        val cocokMurah = if (hanyaMurah) makanan.harga <= 15000 else true

        val cocokKategori = if (kategoriDipilih == "Semua") {
            true
        } else {
            makanan.nama.contains(kategoriDipilih, ignoreCase = true)
        }

        cocokSearch && cocokMurah && cocokKategori
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Halo, ${user?.nama ?: "Pengguna"}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Mau makan apa hari ini?",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Button(
                onClick = { navController.navigate("profile") }
            ) {
                Text(text = "Profil")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = kataKunci,
            onValueChange = { kataKunci = it },
            label = { Text("Cari menu makanan") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Cari"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Menu murah")

            Switch(
                checked = hanyaMurah,
                onCheckedChange = { hanyaMurah = it }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(kategoriList) { kategori ->
                Button(
                    onClick = { kategoriDipilih = kategori },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (kategoriDipilih == kategori) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.secondary
                        }
                    )
                ) {
                    Text(text = kategori)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onRefresh,
            enabled = !isLoadingData,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Muat Ulang Menu")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoadingData) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Memuat data menu...")
            }
        } else {
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            Text(
                text = "Daftar Menu",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(makananTampil) { _, makanan ->
                    MenuCard(
                        makanan = makanan,
                        onDetailClick = {
                            val indexAsli = daftarMakanan.indexOf(makanan).coerceAtLeast(0)
                            navController.navigate("detail/$indexAsli")
                        }
                    )
                }
            }
        }
    }
}
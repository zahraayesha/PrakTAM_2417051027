package com.example.praktam_2417051027

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praktam_2417051027.model.Makanan
import com.example.praktam_2417051027.model.MakananSource
import com.example.praktam_2417051027.ui.theme.PrakTAM_2417051027Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrakTAM_2417051027Theme {
                DaftarMakananScreen()
            }
        }
    }
}

@Composable
fun DaftarMakananScreen() {
    val daftarMakanan = MakananSource.dummyMakanan
    val pilihanFilter = listOf("Semua", "Murah", "Favorit")

    var filterDipilih by remember { mutableStateOf("Semua") }

    val makananTampil = when (filterDipilih) {
        "Murah" -> daftarMakanan.filter { it.harga <= 10000 }
        "Favorit" -> daftarMakanan.filter { it.nama == "Mie Ayam" || it.nama == "Dimsum" }
        else -> daftarMakanan
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Warung Favorit",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Pilihan menu makanan sederhana dengan tampilan yang lebih rapi.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Kategori",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(pilihanFilter) { filter ->
                Button(
                    onClick = {
                        filterDipilih = filter
                    }
                ) {
                    Text(text = filter)
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Menu Tersedia",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(makananTampil) { makanan ->
                ItemMakanan(makanan = makanan)
            }
        }
    }
}

@Composable
fun ItemMakanan(makanan: Makanan) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = makanan.imageRes),
                contentDescription = makanan.nama,
                modifier = Modifier.size(95.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = makanan.nama,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = makanan.deskripsi,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Harga: Rp ${makanan.harga}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { }) {
                    Text(
                        text = "Lihat Detail",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDaftarMakanan() {
    PrakTAM_2417051027Theme {
        DaftarMakananScreen()
    }
}
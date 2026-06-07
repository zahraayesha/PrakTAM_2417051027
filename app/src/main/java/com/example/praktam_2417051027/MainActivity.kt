package com.example.praktam_2417051027

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Daftar Makanan Favorit",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        daftarMakanan.forEach { makanan ->

            ItemMakanan(makanan)

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ItemMakanan(makanan: Makanan) {

    Column {

        Image(
            painter = painterResource(id = makanan.imageRes),
            contentDescription = makanan.nama,
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = makanan.nama,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = makanan.deskripsi,
            fontSize = 12.sp
        )

        Text(
            text = "Harga: Rp ${makanan.harga}",
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Button(onClick = { }) {
            Text("Lihat Detail", fontSize = 11.sp)
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
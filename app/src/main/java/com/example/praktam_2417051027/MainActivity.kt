package com.example.praktam_2417051027

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

    var makananTerpilih by remember {
        mutableStateOf("Belum ada makanan yang dipilih")
    }

    var jumlahKlik by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFBF2))
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Daftar Makanan Favorit",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4E342E)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Pilih makanan untuk melihat perubahan state pada tampilan.",
            fontSize = 13.sp,
            color = Color(0xFF6D4C41)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFFFE0B2),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(14.dp)
        ) {
            Column {
                Text(
                    text = "State Saat Ini",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3E2723)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = makananTerpilih,
                    fontSize = 13.sp,
                    color = Color(0xFF4E342E)
                )

                Text(
                    text = "Jumlah tombol diklik: $jumlahKlik kali",
                    fontSize = 13.sp,
                    color = Color(0xFF4E342E)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        daftarMakanan.forEach { makanan ->
            ItemMakanan(
                makanan = makanan,
                onDetailClick = {
                    makananTerpilih = "${makanan.nama} dipilih dengan harga Rp ${makanan.harga}"
                    jumlahKlik++
                }
            )

            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Composable
fun ItemMakanan(
    makanan: Makanan,
    onDetailClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(id = makanan.imageRes),
            contentDescription = makanan.nama,
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .clip(RoundedCornerShape(14.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = makanan.nama,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3E2723)
        )

        Text(
            text = makanan.deskripsi,
            fontSize = 13.sp,
            color = Color(0xFF6D4C41)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Rp ${makanan.harga}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE65100)
            )

            Button(
                onClick = onDetailClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE65100)
                )
            ) {
                Text(
                    text = "Lihat Detail",
                    fontSize = 12.sp,
                    color = Color.White
                )
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
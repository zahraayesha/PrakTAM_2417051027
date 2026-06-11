package com.example.praktam_2417051027.model

object MakananRepository {

    suspend fun getMakanan(url: String): List<Makanan> {
        return RetrofitClient.apiService.getMakanan(url)
    }

    fun getFallbackMakanan(): List<Makanan> {
        return listOf(
            Makanan(
                nama = "Mie Ayam",
                deskripsi = "Data cadangan ketika Gist gagal dimuat.",
                harga = 12000,
                imageUrl = ""
            ),
            Makanan(
                nama = "Dimsum",
                deskripsi = "Data cadangan ketika Gist gagal dimuat.",
                harga = 15000,
                imageUrl = ""
            ),
            Makanan(
                nama = "Risoles",
                deskripsi = "Data cadangan ketika Gist gagal dimuat.",
                harga = 8000,
                imageUrl = ""
            )
        )
    }
}
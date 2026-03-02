package com.example.praktam_2417051027.model

import androidx.annotation.DrawableRes

data class Makanan(
    val nama: String,
    val deskripsi: String,
    val harga: Int,
    @DrawableRes val imageRes: Int
)


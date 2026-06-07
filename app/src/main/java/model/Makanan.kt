package com.example.praktam_2417051027.model

import com.google.gson.annotations.SerializedName

data class Makanan(
    val nama: String,
    val deskripsi: String,
    val harga: Int,
    @SerializedName("image_name")
    val imageName: String
)
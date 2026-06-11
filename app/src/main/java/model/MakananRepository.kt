package com.example.praktam_2417051027.model

object MakananRepository {

    suspend fun getMakanan(url: String): List<Makanan> {
        return RetrofitClient.apiService.getMakanan(url)
    }

    fun getFallbackMakanan(): List<Makanan> {
        return MakananSource.dummyMakanan
    }
}
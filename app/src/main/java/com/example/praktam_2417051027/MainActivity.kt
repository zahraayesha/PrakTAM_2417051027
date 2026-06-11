package com.example.praktam_2417051027

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.praktam_2417051027.navigation.WarungFavoritApp
import com.example.praktam_2417051027.ui.theme.PrakTAM_2417051027Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrakTAM_2417051027Theme {
                WarungFavoritApp()
            }
        }
    }
}
package com.example.careerlink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.careerlink.data.TokenDataStore
import com.example.careerlink.main.MainNavigation
import com.example.careerlink.ui.theme.CareerlinkTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var tokenDataStore: TokenDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CareerlinkTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainNavigation(tokenDataStore = tokenDataStore)
                }
            }
        }
    }
}


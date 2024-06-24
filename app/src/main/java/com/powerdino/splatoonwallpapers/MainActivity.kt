package com.powerdino.splatoonwallpapers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.SplatoonWallpapersTheme
import com.powerdino.splatoonwallpapers.ui.navigation.NavigationComposable

class MainActivity : ComponentActivity() {
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
        val windowSize = calculateWindowSizeClass(this)
        SplatoonWallpapersTheme {
               Column{
                   NavigationComposable(
                       windowSize = windowSize.widthSizeClass
                   )
               }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun Preview() {
    SplatoonWallpapersTheme {
        NavigationComposable(windowSize = null)
    }
}
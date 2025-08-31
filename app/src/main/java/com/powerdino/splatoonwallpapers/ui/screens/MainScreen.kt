package com.powerdino.splatoonwallpapers.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.compose.SplatoonWallpapersTheme
import com.powerdino.splatoonwallpapers.ui.viewmodel.DownloadViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navyController:NavController?,
    viewModel: DownloadViewModel?,
    windowSize: WindowWidthSizeClass?
){
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        text = "Splatoon Wallpaper",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        }
    ){ innerPadding ->
        Column(
           modifier = Modifier.padding(innerPadding)
        ) {
            MainScreenSecondary(
                navyController,
                viewModel,
                windowSize
            )
        }

    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light",
    backgroundColor = 0xFFFCFCFF
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark",
    backgroundColor = 0xFF1A1C1E
)
@Composable
private fun Preview(){
    SplatoonWallpapersTheme {
       MainScreen(null,null,null)
    }
}


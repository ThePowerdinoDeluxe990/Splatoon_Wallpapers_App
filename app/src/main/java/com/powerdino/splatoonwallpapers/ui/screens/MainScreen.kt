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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.compose.SplatoonWallpapersTheme
import com.powerdino.splatoonwallpapers.R
import com.powerdino.splatoonwallpapers.ui.composable.DropdownTypeSelectorMenu
import com.powerdino.splatoonwallpapers.ui.viewmodel.DownloadViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navyController:NavController?,
    viewModel: DownloadViewModel?,
    windowSize: WindowWidthSizeClass?
){
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var typeOfWallpaper by remember { mutableStateOf(context.getString(R.string.type0)) }

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
                    IconButton(
                        modifier = Modifier.testTag("DropDownButton"),
                        onClick = {
                            expanded = !expanded
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                    DropdownTypeSelectorMenu(
                        changeTypeOfWallpaper = {newTypeOfWallpaper -> typeOfWallpaper = newTypeOfWallpaper},
                        expanded = expanded
                    ) {
                        expanded = false
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
                windowSize,
                typeOfWallpaper = typeOfWallpaper
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


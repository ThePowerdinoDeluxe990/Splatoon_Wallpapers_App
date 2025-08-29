package com.powerdino.splatoonwallpapers.ui.Screens

import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.SplatoonWallpapersTheme
import com.powerdino.splatoonwallpapers.ui.navigation.NavigationComposableScreens
import com.powerdino.splatoonwallpapers.ui.viewmodel.DownloadViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.powerdino.splatoonwallpapers.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DownloadScreen(
        navController: NavController?,
        viewModel: DownloadViewModel?,
    ){

    val localContext = LocalContext.current
    val viewVar by viewModel!!.downloadState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold (

        topBar ={
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        text =  stringResource( viewVar.wallpaperName),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController?.navigate(NavigationComposableScreens.mainScreen.route) }) {
                        Icon(
                            imageVector =  Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {

            ExtendedFloatingActionButton(
                onClick = {
                    val bitmapConvertor = BitmapFactory.decodeResource(localContext.resources, viewVar.wallpaperImageResource)
                    val imageName = localContext.getString(viewVar.wallpaperName)
                    val downloadMessage = localContext.getString(R.string.download_finish)
                    scope.launch {
                        viewModel.saveImage(
                            bitmap = bitmapConvertor,
                            context = localContext,
                            imageName =  imageName
                        )
                        snackbarHostState.showSnackbar(message = "✅ " +downloadMessage)
                    }
                },
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        Icons.Default.Download,
                        "Download Button"
                    )

                    Text(
                        text ="Download ",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ){ innerPadding ->
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(1f)
            .padding(15.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(viewVar.wallpaperImageResource), 
            contentDescription = stringResource( viewVar.wallpaperName),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp)),
        )

        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
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
        DownloadScreen(navController = null, viewModel = null )
    }
}


package com.powerdino.splatoonwallpapers.ui.Screens

import android.content.Context.MODE_PRIVATE
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.SplatoonWallpapersTheme
import com.powerdino.splatoonwallpapers.ui.composable.ItemCard
import com.powerdino.splatoonwallpapers.ui.data.WallpaperList
import com.powerdino.splatoonwallpapers.ui.navigation.NavigationComposableScreens
import com.powerdino.splatoonwallpapers.ui.viewmodel.DownloadViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navControler:NavController?,
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
                }
            )
        }
    ){ innerPadding ->
        Column(
           modifier = Modifier.padding(innerPadding)
        ) {
            MainScreenComposable(
                navControler,
                viewModel,
                windowSize
            )
        }

    }
}

@OptIn(FlowPreview::class)
@Composable
fun MainScreenComposable(
    navControler: NavController?,
    viewModel: DownloadViewModel?,
    windowSize: WindowWidthSizeClass?
){
    var screenSizeVar by remember {
        mutableStateOf(0.dp)
    }
    val context = LocalContext.current
    when(windowSize){
        WindowWidthSizeClass.Expanded -> {
            screenSizeVar = 250.dp
        }
        else ->{
            screenSizeVar = 150.dp
        }
    }
    val prefs by lazy{
        context.getSharedPreferences("prefs",MODE_PRIVATE)
    }

    val scrollPosition = prefs.getInt("scroll_position",0)
    val lazyVerticalStaggeredGridState = rememberLazyStaggeredGridState(
        initialFirstVisibleItemIndex = scrollPosition
    )

    LaunchedEffect(lazyVerticalStaggeredGridState) {
        snapshotFlow {
            lazyVerticalStaggeredGridState.firstVisibleItemIndex
        }
            .debounce(500L)
            .collectLatest { index ->
                prefs.edit()
                    .putInt("scroll_position",index)
                    .apply()
            }
    }

    Column (
        modifier = Modifier.padding(10.dp)
    ){
        LazyVerticalStaggeredGrid(
            state = lazyVerticalStaggeredGridState,
            columns = StaggeredGridCells.Adaptive(screenSizeVar),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalItemSpacing = 14.dp
        ) {
            items(WallpaperList.Wallpapers) { wallpaper ->
                ItemCard(
                    onClickButton = {
                        viewModel?.getDownloadState(

                            wallpaperResource = wallpaper.wallpaperImageResource,
                            wallpaperName = wallpaper.wallpaperName,
                            wallpaperUrl =  context.getString( wallpaper.wallpaperUrl),
                        )
                        navControler?.navigate(NavigationComposableScreens.downloadScreen.route)
                    },
                    wallpaperName = wallpaper.wallpaperName,
                    wallpaperImage = wallpaper.wallpaperImageResource
                )
            }
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


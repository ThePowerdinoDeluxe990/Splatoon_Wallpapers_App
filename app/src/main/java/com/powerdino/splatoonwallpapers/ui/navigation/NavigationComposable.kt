package com.powerdino.splatoonwallpapers.ui.navigation

import android.app.Activity
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.powerdino.splatoonwallpapers.ui.screens.DownloadScreen
import com.powerdino.splatoonwallpapers.ui.screens.MainScreen
import com.powerdino.splatoonwallpapers.ui.viewmodel.DownloadViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavigationComposable(
    viewModel: DownloadViewModel = viewModel(),
    windowSize: WindowWidthSizeClass?
){
    val navControler = rememberNavController()
    val context = LocalContext.current
    NavHost(
        navController = navControler,
        startDestination = NavigationComposableScreens.mainScreen.route
    ){
        composable(route = NavigationComposableScreens.mainScreen.route){
            MainScreen(
                navyController = navControler,
                viewModel = viewModel,
                windowSize = windowSize
            )
            BackHandler(true) {

                val activity = (context as? Activity)
                activity?.finish()
            }
        }

        composable(
            route = NavigationComposableScreens.downloadScreen.route,
        ){
            BackHandler(true) {
                navControler.navigate(NavigationComposableScreens.mainScreen.route)
            }
          DownloadScreen(
              navController = navControler,
              viewModel = viewModel
          )
        }
    }
}
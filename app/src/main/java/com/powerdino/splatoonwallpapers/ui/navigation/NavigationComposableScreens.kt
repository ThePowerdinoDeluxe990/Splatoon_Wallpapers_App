package com.powerdino.splatoonwallpapers.ui.navigation

sealed class NavigationComposableScreens(val route:String) {
    object mainScreen: NavigationComposableScreens("MainScreen")
    object downloadScreen: NavigationComposableScreens("downloadScreen")
}
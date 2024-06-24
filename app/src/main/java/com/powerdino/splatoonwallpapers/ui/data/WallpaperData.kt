package com.powerdino.splatoonwallpapers.ui.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class WallpaperData(
    @DrawableRes val wallpaperImageResource:Int,
    @StringRes val wallpaperName:Int,
    @StringRes val wallpaperUrl:Int
)

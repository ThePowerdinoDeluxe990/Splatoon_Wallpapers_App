package com.powerdino.splatoonwallpapers.ui.viewmodel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ViewModelData(
    @DrawableRes val wallpaperImageResource:Int = 0,
    @StringRes val wallpaperName:Int = 0,
    @StringRes val wallpaperUrl:Int = 0
)

package com.powerdino.splatoonwallpapers.ui.viewmodel

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DownloadViewModel : ViewModel(){
    private val _DownloadState = MutableStateFlow(ViewModelData())
    val downloadState:StateFlow<ViewModelData> = _DownloadState.asStateFlow()

    fun savePhotoBrowser(
        @StringRes urlPhoto:Int,
        uriHandler: UriHandler,
        context: Context
    ){
        uriHandler.openUri(context.resources.getString(urlPhoto))
    }

    fun getDownloadState(
        @DrawableRes wallpaperResource:Int,
        @StringRes wallpaperName:Int,
        @StringRes wallpaperUrl:Int
    ){
        _DownloadState.update { currentState ->
            currentState.copy(
                wallpaperResource,
                wallpaperName,
                wallpaperUrl
            )
        }
    }
}
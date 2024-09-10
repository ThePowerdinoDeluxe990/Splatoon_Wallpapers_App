package com.powerdino.splatoonwallpapers.ui.viewmodel

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.powerdino.splatoonwallpapers.downloader.AndroidDownloader
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DownloadViewModel : ViewModel(){
    private val _DownloadState = MutableStateFlow(ViewModelData())
    val downloadState:StateFlow<ViewModelData> = _DownloadState.asStateFlow()

    fun savePhotoOnDevice(
        urlPhoto:String,
        context: Context
    ){
        val dowloader = AndroidDownloader(context)
        dowloader.downloadFile(
            urlPhoto,
            context.getString(_DownloadState.value.wallpaperName)
        )
    }

    fun getDownloadState(
        @DrawableRes wallpaperResource:Int,
        @StringRes wallpaperName:Int,
        wallpaperUrl:String
    ){
        _DownloadState.update { currentState ->
            currentState.copy(
                wallpaperImageResource = wallpaperResource,
                wallpaperName = wallpaperName,
                wallpaperUrl = wallpaperUrl
            )
        }
    }
}
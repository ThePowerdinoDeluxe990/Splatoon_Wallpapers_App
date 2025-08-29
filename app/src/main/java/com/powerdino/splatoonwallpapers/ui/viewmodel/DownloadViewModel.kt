package com.powerdino.splatoonwallpapers.ui.viewmodel

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class DownloadViewModel() : ViewModel(){
    private val _DownloadState = MutableStateFlow(ViewModelData())
    val downloadState:StateFlow<ViewModelData> = _DownloadState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.Q)
    suspend fun saveImage(bitmap: Bitmap, context: Context, imageName:String){

        withContext(Dispatchers.IO){
            val resolver = context.contentResolver

            val imageCollection = MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL_PRIMARY
            )

            val timeInMilis = System.currentTimeMillis()

            val imageContentValues = ContentValues().apply {
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES
                )
                put(
                    MediaStore.Images.Media.DISPLAY_NAME,
                    imageName + ".jpg"
                )
                put(
                    MediaStore.Images.Media.MIME_TYPE, "image/jpg"
                )
                put(
                    MediaStore.Images.Media.DATE_TAKEN, timeInMilis
                )
                put(
                    MediaStore.Images.Media.IS_PENDING,1
                )
            }
            val imageMediaStoreUri = resolver.insert(
                imageCollection, imageContentValues
            )

            imageMediaStoreUri?.let{ uri ->
                try {
                    resolver.openOutputStream(uri)?.let{ outputStream ->
                        bitmap.compress(
                            Bitmap.CompressFormat.JPEG, 100,
                            outputStream
                        )
                    }
                    imageContentValues.clear()
                    imageContentValues.put(
                        MediaStore.MediaColumns.IS_PENDING, 0
                    )

                    resolver.update(
                        uri, imageContentValues, null, null
                    )
                }catch (e: Exception){
                    e.printStackTrace()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        resolver.delete(uri, null, null)
                    } else {
                        TODO("VERSION.SDK_INT < R")
                    }
                }

            }
        }
    }

    fun getDownloadState(
        @DrawableRes wallpaperResource:Int,
        @StringRes wallpaperName:Int,
    ){
        _DownloadState.update { currentState ->
            currentState.copy(
                wallpaperImageResource = wallpaperResource,
                wallpaperName = wallpaperName,
            )
        }
    }
}
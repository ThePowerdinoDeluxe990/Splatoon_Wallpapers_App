package com.powerdino.splatoonwallpapers.downloader

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader (
    context: Context
):Downloader{
    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String,fileName:String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/jpeg")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("${fileName}.jpeg")
            .addRequestHeader("Authorization","Bearer <token>")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${fileName}.jpeg")
        return downloadManager.enqueue(request)


    }
}
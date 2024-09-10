package com.powerdino.splatoonwallpapers.downloader

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.work.impl.utils.ForceStopRunnable.BroadcastReceiver

@SuppressLint("RestrictedApi")
class DownloadCompleteReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if(intent?.action == "android.intent.action.DOWNLOAD_COMPLETE"){
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
            if(id != -1L){
                Toast.makeText(context, "Download finished!", Toast.LENGTH_SHORT).show()
                Log.e("Info","Download with ID $id finished!")
            }
        }
    }
}
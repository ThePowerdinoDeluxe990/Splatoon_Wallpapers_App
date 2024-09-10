package com.powerdino.splatoonwallpapers.downloader

interface Downloader {
    fun downloadFile(url:String,fileName:String):Long
}
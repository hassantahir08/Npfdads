package com.ashar.naturedual

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import com.google.android.gms.ads.*


class Application : Application() {
    val isAdsShow = true

    override
    fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) { Log.e("TAG", "Loaded Ads") }
        if (isAdsShow)
            OpenAds(this)
    }


    override
    fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
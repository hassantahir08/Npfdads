package com.xiaopo.flying.sticker

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

open class AdsBaseActivity : AppCompatActivity() {

    lateinit var mAdView : AdView
    lateinit var adRequest: AdRequest
    val isAdsShow:Boolean=true
    var interstitial: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun startActivityWithAds( context: Context, intent: Intent)
    {
        if (isAdsShow) {
            requestNewInterstitial()
            val pDialog = ProgressDialog(this)
            pDialog.setMessage(getString(R.string.please_wait))
            pDialog.setTitle(getString(R.string.laoding_text))
            pDialog.isIndeterminate = true
            pDialog.setCanceledOnTouchOutside(false)
            pDialog.setCancelable(false)
            pDialog.show()

            Handler(Looper.getMainLooper()).postDelayed({
                pDialog.dismiss()
                if (interstitial != null) {
                    interstitial!!.show(this)
                    OpenAds.isIntertitalLoaded = true
                    interstitial!!.fullScreenContentCallback =
                        object : FullScreenContentCallback() {
                            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                super.onAdFailedToShowFullScreenContent(adError)
                            }
                            override fun onAdShowedFullScreenContent() {
                                interstitial = null
                            }
                            override fun onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent()
                                OpenAds.isIntertitalLoaded = false
                                context.startActivity(intent)
                            }
                        }
                } else
                    context.startActivity(intent)
            }, 3500)
        } else {
            context.startActivity(intent)
        }

    }

    fun requestNewInterstitial() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            getString(R.string.admob_inter_id),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    // The interstitialAd reference will be null until
                    // an ad is loaded.
                    interstitial = interstitialAd
                    Log.i("TAG", "onAdLoaded")
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    // Handle the error
                    Log.i("TAG", loadAdError.message)
                    interstitial = null
                }
            })
    }

    @SuppressLint("VisibleForTests")
    open fun banner_ad() {
        mAdView = findViewById(R.id.adView)

        if(isAdsShow) {
            if (!mAdView.isLoading) {
                adRequest = AdRequest.Builder().build()
                mAdView.loadAd(adRequest)
            } else {
                mAdView.visibility = View.GONE
            }
            mAdView.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    mAdView.visibility = View.VISIBLE
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    mAdView.visibility = View.GONE
                }
            }
        }else
        {
            mAdView.visibility = View.GONE
        }
    }
}
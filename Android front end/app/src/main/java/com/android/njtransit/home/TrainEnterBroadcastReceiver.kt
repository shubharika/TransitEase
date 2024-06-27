package com.android.njtransit.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class TrainEnterBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.i("Broadcast","WHAT THE HEALLALALALALA BOYYYYYYYYYYYYYY")
        StringBuilder().apply{
            append("Action: ${p1?.action}\n")
            append("URI: ${p1?.toUri(Intent.URI_INTENT_SCHEME)}\n")
            toString().also { log ->
                Log.d("MyBroadcastReciever", log)

                val packageManager = p0?.packageManager
                val launchIntent = packageManager?.getLaunchIntentForPackage("com.android.njtransit.MainActivity")
                launchIntent?.putExtra("HELLOW","WORLD")
                p0?.startActivity(launchIntent)
            }
        }
    }
}
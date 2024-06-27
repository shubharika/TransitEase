package com.android.njtransit

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var nfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        Log.i("ELLO", "WORLD")
        val nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if(nfcAdapter == null){
            Log.i("NFC Not Supported", "log")
        }
        else{
            Log.i("NFC","Supported")
            readFromIntent(this.intent)
        }
    }

    fun readFromIntent(intent: Intent){
        val action = intent.action
        Log.i("HERE","IAM")
        Log.i("Action", action.toString())
        if(NfcAdapter.ACTION_TAG_DISCOVERED == action||NfcAdapter.ACTION_TECH_DISCOVERED == action||NfcAdapter.ACTION_NDEF_DISCOVERED == action){
            Log.i("TAG","Discovered")

        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.i("THIS", "IS US")
        if(NfcAdapter.ACTION_NDEF_DISCOVERED == intent?.action){
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
                val message: List<NdefMessage> = rawMessages.map { it as NdefMessage }
                Log.i("ID", message.toString())
            }
        }
    }
}
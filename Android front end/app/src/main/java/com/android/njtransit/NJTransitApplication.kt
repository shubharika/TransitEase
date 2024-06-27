package com.android.njtransit

import android.app.Application
import com.android.njtransit.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltAndroidApp
class NJTransitApplication: Application(){
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}
package com.android.njtransit.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val trainId : Int = 0,
    val timestamp: Int = 0,
    val walletBallance: Int = 0

)
